# Entity Minr #

## Copy dependencies ##

Clone this repo to a convienient folder.
Download the [Stanford Named Enity Recognizer](http://nlp.stanford.edu/downloads/CRF-NER.shtml#Download) and copy the `stanford-ner.jar` into the `./lib` folder of this repo.
Copy the `./classifiers/english.all.3class.distsim.crf.ser.gz` model from into the `./stanford` folder of this repo.

## Build & Run ##

```sh
$ cd Entity_Minr
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
