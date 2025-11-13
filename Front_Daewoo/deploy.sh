#!/bin/bash
set -e

# ===== Variables =====
CONTAINER_NAME="front-container"
IMAGE_NAME="front-image"
NETWORK_NAME="daewoo_a_network"

# Ports (host:container)
HOST_PORT="780"
CONTAINER_PORT="80"

# ===== Script =====
echo "✅ 최신화"
git pull | true


echo "✅ 기존 컨테이너 중지"
docker stop "$CONTAINER_NAME" || true

echo "✅ 기존 컨테이너 제거"
docker rm "$CONTAINER_NAME" || true

echo "✅ 기존 이미지 제거"
docker rmi "$IMAGE_NAME" || true

echo "✅ 프로젝트 빌드하기"
npm run build || true

echo "✅ new 이미지 생성"
docker build -t "$IMAGE_NAME" . || true

echo "✅ new 컨테이너 켜기"
docker run -d \
  -p "$HOST_PORT:$CONTAINER_PORT" \
  --name "$CONTAINER_NAME" \
  --net "$NETWORK_NAME" \
  "$IMAGE_NAME" || true

