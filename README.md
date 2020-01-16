# rest-assured-demo
To try new stuff in [RestAssured](https://github.com/rest-assured/rest-assured/wiki/Usage)

## json schema validation
RestAssured supports schemas up to draft4. 
There is no support for draft 6 and 7. 
Try other [libs for sdraft -07, -06](https://json-schema.org/implementations.html#validator-java)

## Another useful tools:
Also tried in this project:
### CircleCi
Login to `https://circleci.com` with GitHub or BitBucket creds and add your project
Accessible on `https://circleci.com/gh/[github-user-name]/[repo-name]`
Creates pipeline config file e.g. `.circleci/config.yml` with command `run: gradle test`
### GitHub CI
Accessible  through Actions tab in the repo on GitHub. Creates pipeline config file e.g. `.github/workflows/radle
.yaml` with a command `run: ./gradlew build`
### REST data provider
Provides data through REST in JSON format, very handy to start TA project or framework
#### my-json-server
Mock your data through `https://my-json-server.typicode.com/[github-user-name]/[repo-name]`
#### jsonplaceholder
Use some data provided at `https://jsonplaceholder.typicode.com`