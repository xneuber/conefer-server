# conefer-server
 Sistema de Gerenciamento de Estoque

#Variáveis de Ambiente (VM Args)
 Copiar o texto abaixo para a área de transferência;
 Clicar no Select do lado esquerdo superior do InteliJ;
 Ir em Edit Configurations;
 No campo "VM Options" colar o conteúdo da área de transferência;
 Atente-se que, caso tenha uma senha ou usuário diferente para o seu banco local, deve trocar conforme 
for o caso, abaixo está como default.

```
-Dspring.profiles.active=dev
-Dconefer.datasource.url=jdbc:postgresql://localhost:5432/conefer
-Dconefer.datasource.username=postgres
-Dconefer.datasource.password=123456
```