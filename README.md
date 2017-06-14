# SSP

## Install SSP dependencies

```
git clone git@github.com:projekt-internet-technologien/smart-service-proxy.git
cd smart-service-proxy
mvn install
```

## Clone and build

```
git clone git@github.com:projekt-internet-technologien/SSP.git
cd SSP
mvn package
```

## Configure

Open `ssp.properties`:

* `coap.port` configures the port of the CoAP endpoint (default: 5683)

## Run

```
java -jar target/SSP-0.0.1-SNAPSHOT.jar
```

Hint: log4j.xml + ssp.properties must be in the root folder
