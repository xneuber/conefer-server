# conefer-server
 Sistema de Gerenciamento de Estoque

#Variáveis de Ambiente (VM Args)
1. Copiar o texto abaixo para a área de transferência;
2. Clicar no Select do lado esquerdo superior do InteliJ; 
3. Ir em Edit Configurations;
4. No campo "VM Options" colar o conteúdo da área de transferência;
5-Atente-se que, caso tenha uma senha ou usuário diferente para o seu banco local, deve trocar conforme 
for o caso, abaixo está como default.

```
-Dspring.profiles.active=dev
-Dconefer.datasource.url=jdbc:postgresql://localhost:5432/conefer
-Dconefer.datasource.username=postgres
-Dconefer.datasource.password=123456
```