default: build run

build:
	mvn clean package

run:
	sudo chmod 777 -R docker/grafana && docker-compose up --build

gatling:
	mvn clean gatling:test -Dgatling.simulationClass=lin.louis.poc.benchmark.CatSimulation
