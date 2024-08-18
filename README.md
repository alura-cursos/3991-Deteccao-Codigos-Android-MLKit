![detecção-codigos-banner](https://github.com/user-attachments/assets/770696fd-ebaf-469d-b594-88bc127986c7)

# ConfereAi
Um aplicativo Android desenvolvido para ser utilizado em eventos de tecnologia realizados em grandes espaços. Ele permite a identificação de códigos QR e a extração das informações codificadas neles. Com essas informações, o app pode adicionar eventos à agenda do celular, abrir sites, obter informações de redes de conexão, abrir mapas, e muito mais!

## 🔨 Funcionalidades do projeto


https://github.com/user-attachments/assets/8e24cd97-308f-401e-a5f7-26e756085cfc





### 📱 Telas
- **Início:** Lista todos os tipos de códigos já escaneados, exibindo-os como uma espécie de medalha para cada código encontrado.
- **Scanner ML Kit:** Identifica os códigos e exibe as informações diretamente na tela.
- **Scanner Nativo:** Identifica os códigos e exibe as informações na tela de detalhes.
- **Detalhes:** Exibe as informações de cada tipo de código, junto com dados pré-cadastrados, além de um botão que realiza diferentes tipos de ações com base no código e nas informações extraídas.


## ✔️ Técnicas e tecnologias utilizadas

As técnicas e tecnologias utilizadas pra isso são:

- `Jetpack Compose`: kit de ferramentas moderno para criar IUs em dispositivos móveis.
- `Kotlin`: linguagem de programação.
- `Gradle Version Catalogs`: nova forma de gerenciar plugins e dependências em projetos Android.
- `Material Design 3`: padrão de design recomendado pela Google para criação de UI modernas.
- `Hilt`: injeção de dependências.
- `Navigating with Compose`: navegação entre composables e telas.
- `Viewmodel, states e flow`: gerenciamento de estados e controle dos eventos disparados pelas detecções do modelo da Google.
- `DataStore`: mantém, após o fechamento do app, a última pontuação mais alta e a câmera preferida para usar no jogo.
- `ML Kit Barcode Scanning`: biblioteca para leitura de códigos de barras e QR codes, permitindo a detecção e interpretação rápida e precisa desses códigos a partir da câmera, utilizando modelos de aprendizado de máquina.
- `Google Code Scanner`: ferramenta nativa do Google para escaneamento de códigos de barras e QR codes, oferecendo uma solução simples e eficiente para capturar e interpretar códigos diretamente a partir da câmera do dispositivo, sem a necessidade de integrações complexas.
- `CameraX`: biblioteca do Jetpack que facilita a integração de funcionalidades de câmera em aplicativos Android, abstraindo a complexidade da API de câmera do Android e oferecendo uma interface simples para captura de fotos e vídeo.
- `Camera Permissions`: gerencia o acesso à câmera do dispositivo, solicitando permissão ao usuário para utilizá-la nas detecções e interações dentro do aplicativo.
- `CameraAnalyzer`: componente utilizado junto ao CameraX para analisar frames de vídeo em tempo real, permitindo a implementação de funcionalidades de detecção que requeiram processamento frame a frame.


## 📁 Acesso ao projeto

- Versão inicial: Veja o [código fonte][codigo-inicial] ou [baixe o projeto][download-inicial]
- Versão final: Veja o [código fonte][codigo-final] ou [baixe o projeto][download-final]

## 🛠️ Abrir e rodar o projeto
Após baixar o projeto, você pode abri-lo com o Android Studio. Para isso, na tela de launcher clique em:

“Open” (ou alguma opção similar), procure o local onde o projeto está e o selecione (caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo). Por fim, clique em “OK” o Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆


## 📚 Mais informações do curso

Gostou do projeto e quer conhecer mais? Você pode [acessar a formação com esse e muitos outros cursos](https://www.alura.com.br/formacao-android-ia-google-ml-kit) relacioandos ao tema de Machine Learning e IA no Android.

[codigo-inicial]: https://github.com/git-jr/3991-Deteccao-Codigos-Android-MLKit/commits/projeto-inicial/
[download-inicial]: https://github.com/git-jr/3991-Deteccao-Codigos-Android-MLKit/archive/refs/heads/projeto-inicial.zip

[codigo-final]: https://github.com/git-jr/3991-Deteccao-Codigos-Android-MLKit/commits/aula-5/
[download-final]: https://github.com/git-jr/3991-Deteccao-Codigos-Android-MLKit/archive/refs/heads/aula-5.zip
