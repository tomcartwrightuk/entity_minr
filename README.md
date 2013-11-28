# Entity Minr #

## Copy dependencies ##

Clone this repo to a convienient folder.
Download the [Stanford Named Enity Recognizer](http://nlp.stanford.edu/downloads/CRF-NER.shtml#Download) and copy the `stanford-ner.jar` into the `./lib` folder of this repo.
Copy the `./classifiers/english.all.3class.distsim.crf.ser.gz` model into the `./stanford` folder of this repo.

## Build & Run ##

```sh
$ cd Entity_Minr
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

## Usage ##

This app has one POST endpoint at `/entities`. Pass the text to process in the request body with the key 'contents'.
For example in ruby using NET::HTTP:

```
uri = URI.parse("http://localhost:8080/entities")

header = {'Content-Type' => 'application/json'}
to_process = {:content => "Paris and Berlin do not share the UK's level of concerns"}

http = Net::HTTP.new(uri.host, uri.port)
request = Net::HTTP::Post.new(uri.request_uri, header)
res.body = to_process.to_json
res = http.request(request)

JSON.parse(res.body)
=>
[{"entity_type"=>"LOCATION", "entity_text"=>"Paris"}, {"entity_type"=>"LOCATION", "entity_text"=>"Berlin"}, {"entity_type"=>"LOCATION", "entity_text"=>"UK"}]
```
