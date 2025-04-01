# 1. Build 단계
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew  # 실행 권한 추가
RUN ./gradlew clean build

# 2. Run 단계
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 빌드 결과물 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 노출 (Spring Boot 기본 포트)
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]