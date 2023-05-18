# Pratica-02

Crie uma classe Edificio para representar todos os seus apartamentos e seus
respectivos moradores, seguindo os itens a seguir:
1. A lista de “Apartamentos” (atributo da classe Edificio) deverá ser uma lista da
classe “Apartamento”.
2. Na classe “Edificio”, implemente um método adicionarApartamento() para adicionar
os apartamentos dentro da lista criada.
3. Na classe “Edificio”, implemente um método mostrarApartamentos(), que possa
imprimir a lista de todos os apartamentos do Edificio.

## Execução do projeto

O projeto é um projeto Maven padrão. Para executá-lo a partir da linha de comando,
digite `mvnw` (Windows) ou `./mvnw` (Mac e Linux) e abra
http://localhost:8080 em seu navegador.

Você também pode importar o projeto para o IDE de sua escolha como faria com qualquer
Projeto Maven. Leia mais sobre [como importar projetos Vaadin para diferentes IDEs](https://vaadin.com/docs/latest/guide/step-by-step/importing) (Eclipse, IntelliJ IDEA, NetBeans e VS Code).

## Implantando para produção

Para criar uma compilação de produção, execute `mvnw clean package -Pproduction` (Windows),
ou `./mvnw clean package -Pproduction` (Mac e Linux).
Isso criará um arquivo JAR com todas as dependências e recursos front-end,
pronto para ser implantado. O arquivo pode ser encontrado na pasta `target` após a conclusão da compilação.

Depois que o arquivo JAR é criado, você pode executá-lo usando
`java -jar target/pratica-02-1.0-SNAPSHOT.jar`

## Estrutura do projeto

- `MainLayout.java` em `src/main/java` contém a configuração de navegação (ou seja, a
  barra lateral/superior e menu principal). Esta configuração usa
  [App Layout](https://vaadin.com/docs/components/app-layout).
- O pacote `views` em `src/main/java` contém as visualizações Java do lado do servidor de sua aplicação.
- A pasta `views` em `frontend/` contém as visualizações JavaScript do lado do cliente do seu aplicativo.
- A pasta `themes` em `frontend/` contém os estilos CSS personalizados.

## Referências

- [Documentação do vaadin](https://vaadin.com/docs/latest/)
- [Tutorial do vaadin](https://vaadin.com/docs/latest/tutorial/overview)
- https://github.com/mourarezendecas/devfullstack


