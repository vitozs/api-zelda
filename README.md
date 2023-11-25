# api-zelda

## Configuracao de ambiente

Buildar imagem docker do postgres, que ja possui a configuracao de senha, nome de banco e querys

``docker build ./resources/ -t usersdb_image``

Subir o container baseado na imagem buildada

``docker run -p 5432:5432 --name dbusers -d usersdb_image``
 
