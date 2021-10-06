# drs-verify

DRS Verify service provides API for verifying ingest and update of OCFL objects in S3.

## API

### Verify

Verify ingest of OCFL object.

**URL** : `/verify/{id}`

**Method** : `POST`

**Data constraints**

Provide key checksum pairs of entire OCFL manifest.

```json
{
    "[key]": "[checksum]"
}
```

**Data example** All manifest entries must be included.

```json
{
    "1254654/v00001/content/data/400005076.mp3": "610e9c6768c1e41b0f997776861308a1",
    "1254654/v00001/content/metadata/400005074_aes57.xml": "1d50396a886425b9d8c7e50461d7b523",
    "1254654/v00001/content/data/400005073.wav": "23a8548521d63065d58dcbafb82dbeb3",
    "1254654/v00001/content/metadata/400005072_textMD.xml": "6f559e7352abee9d8972447eda23cc84",
    "1254654/v00001/content/descriptor/400005067_mets.xml": "bea2ca7c6ec1f1b70ef85265aa396dbd",
    "1254654/v00001/content/metadata/400005076_aes57.xml": "563295c50220c295e317cc6f5c37ee8b",
    "1254654/v00001/content/metadata/400005073_aes57.xml": "1f982931f12de9309157b4e891e0abc7",
    "1254654/v00001/content/metadata/400005079_containerMD.xml": "d5504e692af05bceb4e4760b3700c07f",
    "1254654/v00001/content/data/400005069.xml": "ece0d3d596258f4215c3bc35b0b36e34",
    "1254654/v00001/content/data/400005078.mp3": "1db61ef8c9f1243b80837d52895d688b",
    "1254654/v00001/content/data/400005074.wav": "327b95b2c32f1fa36436c0a12ae5bbf8",
    "1254654/v00001/content/data/400005079.zip": "4b59de5306cfa1e3797febec199e9708",
    "1254654/v00001/content/metadata/400005070_aes57.xml": "db0b43dbf03894bbdb1ddb53768448d9",
    "1254654/v00001/content/data/400005072.adl": "a6e126e393d9917e300394d57756ffac",
    "1254654/v00001/content/metadata/400005078_aes57.xml": "ed97471a97dc39a13b951afe7a34fb71",
    "1254654/v00001/content/data/400005070.wav": "cfd9b3e993c7b030f4807688cddbd77c",
    "1254654/v00001/content/data/400005077.mp3": "61233f404ce85c80a17f521cab99feb5",
    "1254654/v00001/content/metadata/400005067_structureMap.xml": "fa511483e64be6ef2b7c9e07543df158",
    "1254654/v00001/content/metadata/400005069_textMD.xml": "94c551c460d935f3148ebbd06d5412cb",
    "1254654/v00001/content/metadata/400005071_aes57.xml": "cb81e8172c153649be8e5e59236f43c3",
    "1254654/v00001/content/metadata/400005077_aes57.xml": "67aba5ae05507a320f701de7adb60633",
    "1254654/v00001/content/data/400005075.adl": "60da72142a630e223a205877e29be9c9"
}
```

## Success Response

**Condition** : If object is completely verified.

**Code** : `200 OK`

## Error Responses

**Condition** : If something went wrong on server.

**Code** : `500 INTENRAL SERVER ERROR`

**Content** : `Exception message`

### Or

**Condition** : If inventory.json not found.

**Code** : `404 NOT FOUND`

**Content** : `Exception message`

### Or

**Condition** : Verification failed

**Code** : `409 CONFLICT`

**Content example**

```json
{
    "1254654/v00001/content/data/400005076.mp3": {
        "error": "Missing input checksum"
    },
    "1254654/v00001/content/metadata/400005074_aes57.xml": {
        "error": "Unknown key"
    },
    "1254654/v00001/content/data/400005073.wav": {
        "error": "S3 exception message"
    },
    "1254654/v00001/content/metadata/400005072_textMD.xml": {
        "error": "Checksums do not match",
        "expected": "d5504e692af05bceb4e4760b3700c07d",
        "actual": "d5504e692af05bceb4e4760b3700c07f"
    },
    "1254654/v00001/content/metadata/400005079_containerMD.xml": {
        "error": "Checksums do not match",
        "expected": "d5504e692af05bceb4e4760b3700c07d",
        "actual": "d5504e692af05bceb4e4760b3700c07f"
    }
}
```

### Verify Update

Verify update of OCFL object.

**URL** : `/verify/{id}/update`

**Method** : `POST`

**Data constraints**

Provide key checksum pairs of updated OCFL manifest entries.

```json
{
    "[key]": "[checksum]"
}
```

**Data example** Update manifest entries must be included.

```json
{
    "1254654/v00001/content/data/400005076.mp3": "610e9c6768c1e41b0f997776861308a1",
    "1254654/v00001/content/metadata/400005074_aes57.xml": "1d50396a886425b9d8c7e50461d7b523",
    "1254654/v00001/content/data/400005073.wav": "23a8548521d63065d58dcbafb82dbeb3",
    "1254654/v00001/content/metadata/400005072_textMD.xml": "6f559e7352abee9d8972447eda23cc84",
    "1254654/v00001/content/descriptor/400005067_mets.xml": "bea2ca7c6ec1f1b70ef85265aa396dbd",
    "1254654/v00001/content/metadata/400005076_aes57.xml": "563295c50220c295e317cc6f5c37ee8b",
    "1254654/v00001/content/metadata/400005073_aes57.xml": "1f982931f12de9309157b4e891e0abc7",
    "1254654/v00001/content/metadata/400005079_containerMD.xml": "d5504e692af05bceb4e4760b3700c07f",
    "1254654/v00001/content/data/400005069.xml": "ece0d3d596258f4215c3bc35b0b36e34",
    "1254654/v00001/content/data/400005078.mp3": "1db61ef8c9f1243b80837d52895d688b"
}
```

## Success Response

**Condition** : If updated checksums verified.

**Code** : `200 OK`

## Error Responses

**Condition** : If something went wrong on server.

**Code** : `500 INTENRAL SERVER ERROR`

**Content** : `Exception message`

### Or

**Condition** : If inventory.json not found.

**Code** : `404 NOT FOUND`

**Content** : `Exception message`

### Or

**Condition** : Verification failed

**Code** : `409 CONFLICT`

**Content example**

```json
{
    "1254654/v00001/content/data/400005076.mp3": {
        "error": "Missing input checksum"
    },
    "1254654/v00001/content/metadata/400005074_aes57.xml": {
        "error": "Unknown key"
    },
    "1254654/v00001/content/data/400005073.wav": {
        "error": "S3 exception message"
    },
    "1254654/v00001/content/metadata/400005072_textMD.xml": {
        "error": "Checksums do not match",
        "expected": "d5504e692af05bceb4e4760b3700c07d",
        "actual": "d5504e692af05bceb4e4760b3700c07f"
    },
    "1254654/v00001/content/metadata/400005079_containerMD.xml": {
        "error": "Checksums do not match",
        "expected": "d5504e692af05bceb4e4760b3700c07d",
        "actual": "d5504e692af05bceb4e4760b3700c07f"
    }
}
```

### Health

Actuator health endpoint.

**URL** : `/actuator/health`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "status": "UP",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 269490393088,
                "free": 229776134144,
                "threshold": 10485760,
                "exists": true
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
```

### Info

Actuator info endpoint.

**URL** : `/actuator/info`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "build": {
        "artifact": "verify",
        "name": "edu.harvard.drs:verify",
        "description": "DRS Ingest Verification Service",
        "version": "0.0.1-SNAPSHOT"
    }
}
```

### Logfile

Actuator logfile endpoint.

**URL** : `/actuator/logfile`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Content example**

```text
2021-10-05 10:32:09.314  INFO 33360 --- [main] e.h.drs.verify.VerifyApplicationTests    : Starting VerifyApplicationTests using Java 11.0.11 on 254338SD-OP7760 with PID 33360 (started by william_welling in C:\Users\william_welling\Development\harvard\drs-verify)
2021-10-05 10:32:09.327 DEBUG 33360 --- [main] e.h.drs.verify.VerifyApplicationTests    : Running with Spring Boot v2.5.5, Spring v5.3.10
2021-10-05 10:32:09.328  INFO 33360 --- [main] e.h.drs.verify.VerifyApplicationTests    : The following profiles are active: development
2021-10-05 10:32:09.433 DEBUG 33360 --- [main] o.s.w.c.s.GenericWebApplicationContext   : Refreshing org.springframework.web.context.support.GenericWebApplicationContext@cbd9494
2021-10-05 10:32:12.405  INFO 33360 --- [main] e.h.drs.verify.VerifyApplicationTests    : Started VerifyApplicationTests in 3.956 seconds (JVM running for 5.739)
```

## Environment

Here are some of the environment variables that can be set. See [application.yml](https://github.com/wwelling/drs-verify/blob/main/src/main/resources/application.yml) configuration that can be set via environment variables.

| Variable                          | Description                                | Default                             |
| --------------------------        | ------------------------------------------ | ----------------------------------- |
| LOGGING_FILE_PATH                 | path to store logs                         | logs                                |
| LOGGING_LEVEL_EDU_HARVARD_DRS     | drs package log level                      | DEBUG                               |
| LOGGING_LEVEL_ORG_SPRINGFRAMEWORK | spring log level                           | INFO                                |
| LOGGING_LEVEL_WEB                 | we log level                               | INFO                                |
| SERVER_PORT                       | port service listening on                  | 9000                                |
| SPRING_PROFILES_ACTIVE            | active profile                             | development                         |
| EXTERNAL_STAGING_PATH             | external storage path                      | external                            |
| AWS_BUCKET_NAME                   | AWS S3 bucket name                         | drs-preservation                    |
| AWS_REGION                        | AWS region                                 | us-east-1                           |
| AWS_ACCESS_KEY_ID                 | AWS access key id                          | foo                                 |
| AWS_SECRET_ACCESS_KEY             | AWS secret access key                      | bar                                 |
| AWS_ENDPOINT_OVERRIDE             | AWS endpoint override                      |                                     |


## Run

Build
```
mvn clean package
```

Run
```
java -jar target\verify-<version>.jar
```

## Development

Start with devtools
```
mvn clean spring-boot:run
```

## Test

Run Tests
```
mvn clean test
```

## Docker

Build
```
docker build --no-cache -t drs-verify .
```

Run
```
docker run -v /external:/external -v /logs:/logs --env-file=.env drs-verify
```

## Docker Compose

Build image for local use
```
docker-compose -f docker-compose.yml build --no-cache
```

Build image for repository
```
docker-compose -f docker-compose.build.yml build --no-cache
```

Run
```
docker-compose -f docker-compose.yml up
```

> If using docker, do not forget to copy `.env.example` to `.env` and update accordingly.
