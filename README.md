# Scalding Project Template

Este es un template para crear proyectos de trabajos MapReduce con Scalding. Una vil copia de https://github.com/snowplow/scalding-example-project 

Si al momento de hacer assembly sale un error de nombre de archivo muy largo:

'''bash
rm -rf ./target
mkdir /tmp/`echo $$`
ln -s /tmp/`echo $$` ./target
'''

Para ejecutar usando el cliente Ruby de línea de comando:

'''bash
elastic-mapreduce --create --name "<nombre-trabajo>" --jar s3n://<Ubicación del jar en S3> --arg <Clase del job> --arg --hdfs --arg --input --arg s3n://<Ubicación dentro de s3 del input> --arg --output --arg s3n://<Ubicación dentro de s3 del output>
''' 

[scalding-example-project]: https://github.com/snowplow/scalding-example-project
