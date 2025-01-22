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
