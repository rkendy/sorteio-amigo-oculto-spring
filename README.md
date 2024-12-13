# SorteioAmigoOculto

Sorteio de Amigo Oculto e envio de email de notificação

# Configurar titulo

Em application.properties, em app.titulo

# Participantes:

Incluir cada participante no arquivo participantes.txt

# Configurar senha/acesso:
https://myaccount.google.com/lesssecureapps
https://support.google.com/accounts/answer/185833


# Email do remetente e senha:

Configurar o remetente e senha com:
export EMAIL_REMETENTE_SORTEIO=remetente@email.exemplo.com
export EMAIL_SENHA_SORTEIO=senha

# Executar:

./gradlew bootrun
