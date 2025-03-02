#!/bin/bash

# Остановка и удаление всех контейнеров
echo "Остановка всех контейнеров..."
docker stop $(docker ps -a -q) 2>/dev/null
echo "Удаление всех контейнеров..."
docker rm $(docker ps -a -q) 2>/dev/null

# Создание директории для хранения данных на хосте
DATA_DIR="./postgres_data/photocatalog"
mkdir -p $DATA_DIR

# Запуск нового контейнера PostgreSQL с созданием базы данных и томом для данных
echo "Запуск контейнера PostgreSQL..."
docker run --name country \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_DB=photocatalog \
  -v "$DATA_DIR:/var/lib/postgresql/data" \
  -d postgres:15.1 --max_prepared_transactions=100

# Проверка статуса контейнера
if [ $? -eq 0 ]; then
  echo "Контейнер PostgreSQL успешно запущен."
else
  echo "Ошибка при запуске контейнера PostgreSQL."
fi

docker run --name=zookeeper -e ZOOKEEPER_CLIENT_PORT=2181 -p 2181:2181 -d confluentinc/cp-zookeeper:7.3.2
docker run --name=kafka -e KAFKA_BROKER_ID=1 \
-e KAFKA_ZOOKEEPER_CONNECT=$(docker inspect zookeeper --format='{{ .NetworkSettings.IPAddress }}'):2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
-e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
-e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
-p 9092:9092 -d confluentinc/cp-kafka:7.3.2
