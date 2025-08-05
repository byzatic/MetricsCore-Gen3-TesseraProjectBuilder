# MCg3 Project Builder

Скачать проект: NOT-DISTRIBUTED-YET [metricscore-gen3-tessera-project-builder](...)

📦 Требования:
•	Установлена Java 17+
•	JAR-файл metricscore-gen3-tessera-project-builder-1.0-SNAPSHOT-jar-with-dependencies.jar находится в текущем каталоге

⸻

🚀 Инструкция по запуску:
1.	Откройте терминал и перейдите в каталог с JAR-файлом:

```shell
cd /путь/к/вашему/файлу
```

2.	Создайте директории input и output, если они отсутствуют:

```shell
mkdir -p input output
```

3.	Запустите JAR-файл:

```shell
java -DinputFile <path to input file> -DoutputFile <path to output file> -jar metricscore-gen3-tessera-project-builder-1.0-SNAPSHOT-jar-with-dependencies.jar 
```


⸻

🗂 После запуска:
•	Программа будет использовать папку input для входных данных например [example.project.yml](input%2Fexample.project.yml)
•	Результаты будут помещены в папку output

Если программе нужны аргументы — уточните, и я добавлю их в инструкцию.

---
### Работа с mcg3 builder toolbox
Просмотр справки --help
```shell
docker run --rm -v $(pwd):/data docker.io/byzatic/metricscore-gen3-tessera-project-builder:latest -c 'build -i ./project.yml -o ./compiled_project.zip'
```
Ключи для программы **build** (внутри контейнера)

`-f`, `--file`         Указать путь к yaml с описанием проекта

`-o`, `--output`       Указать путь сохранения скомпилированного проекта

`-h`, `--help`         Открыть справку

Пример:
```shell
docker run --rm -v $(pwd):/data docker.io/byzatic/metricscore-gen3-tessera-project-builder:latest sh -c 'build --help'
```

1