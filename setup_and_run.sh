#!/usr/bin/env bash
set -euo pipefail

# Comprobar herramientas necesarias
for cmd in ant mysql wget unzip; do
  command -v $cmd >/dev/null 2>&1 || { echo "Error: '$cmd' no encontrado. Instálalo e inténtalo de nuevo."; exit 1; };
done

# 1. Crear lib/ y descargar dependencias
mkdir -p lib
# Eliminar JARs vacíos o corruptos previos
find lib -type f -size 0 -delete
# Eliminar versión antigua/corrupta de JCalendar si queda
rm -f lib/jcalendar-1.4.0.jar

JDBC_VER=8.0.33
JDBC_JAR=mysql-connector-j-${JDBC_VER}.jar
# JCalendar version
echo "Usando JCalendar 1.3.2"
JCAL_VER=1.3.2
JCAL_JAR=jcalendar-${JCAL_VER}.jar

if [ ! -s lib/$JDBC_JAR ]; then  # re-descarga si el archivo no existe o está vacío
  echo "Descargando $JDBC_JAR..."
  if wget -O lib/$JDBC_JAR https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/${JDBC_VER}/${JDBC_JAR}; then
    echo "$JDBC_JAR descargado."
  else
    echo "Error descargando $JDBC_JAR"; exit 1;
  fi
fi

if [ ! -s lib/$JCAL_JAR ]; then
  echo "Descargando $JCAL_JAR desde Maven Central..."
  if wget -O lib/$JCAL_JAR "https://repo1.maven.org/maven2/com/toedter/jcalendar/${JCAL_VER}/${JCAL_JAR}"; then
    echo "$JCAL_JAR descargado."
  else
    echo "Error descargando $JCAL_JAR"; exit 1;
  fi
fi

# 2. Backup y edición de project.properties
cp nbproject/project.properties nbproject/project.properties.bak

grep -q "file.reference.mysql-connector" nbproject/project.properties || cat <<EOL >> nbproject/project.properties
file.reference.mysql-connector=lib/${JDBC_JAR}
file.reference.jcalendar=lib/${JCAL_JAR}
EOL

sed -i "/^javac.classpath=/c\
javac.classpath=\\
    \${file.reference.mysql-connector}:\\
    \${file.reference.jcalendar}" nbproject/project.properties

sed -i "/^run.classpath=/c\
run.classpath=\\
    \${javac.classpath}:\\
    \${build.classes.dir}" nbproject/project.properties

# 3. Configurar base de datos y usuario
echo "Configurando base de datos y usuario 'biblioteca'..."
if [ $EUID -eq 0 ]; then
  mariadb <<EOF
CREATE DATABASE IF NOT EXISTS dbbiblioteca CHARACTER SET utf8;
CREATE USER IF NOT EXISTS 'biblioteca'@'localhost' IDENTIFIED BY 'biblioteca';
GRANT ALL PRIVILEGES ON dbbiblioteca.* TO 'biblioteca'@'localhost';
FLUSH PRIVILEGES;
EOF
else
  sudo mariadb <<EOF
CREATE DATABASE IF NOT EXISTS dbbiblioteca CHARACTER SET utf8;
CREATE USER IF NOT EXISTS 'biblioteca'@'localhost' IDENTIFIED BY 'biblioteca';
GRANT ALL PRIVILEGES ON dbbiblioteca.* TO 'biblioteca'@'localhost';
FLUSH PRIVILEGES;
EOF
fi

# 4. Compilación y ejecución manual

echo "Limpiando clases anteriores..."
rm -rf build/classes
mkdir -p build/classes

echo "Compilando con javac y librerías de lib/..."
SRC_FILES=$(find src -name '*.java')
javac -cp "lib/*" -d build/classes $SRC_FILES

# Copiar recursos (imágenes, etc.) preservando estructura de carpetas
rsync -a --exclude='*.java' src/ build/classes/

echo "Ejecutando la aplicación (Main)..."
java -cp "build/classes:lib/*" SistemaBiblioteca.Main
