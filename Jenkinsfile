pipeline {
    agent any
    environment {
        DOCKER_PASSWORD = credentials("docker_password")
        DOCKER_USER = credentials("docker_user")
        GITHUB_TOKEN = credentials("github_token")
        IMAGE_NAME = "kickfounder"
    }

    stages {
        stage('Build & Test') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Tag image') {
            steps {
                script {
                    sh([script: 'git fetch --tag', returnStdout: true]).trim()
                    env.MAJOR_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 1', returnStdout: true]).trim()
                    env.MINOR_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 2', returnStdout: true]).trim()
                    env.PATCH_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 3', returnStdout: true]).trim()
                    IMAGE_VERSION = "${env.MAJOR_VERSION}.\$((${env.MINOR_VERSION} + 1)).${env.PATCH_VERSION}"
                }
                sh """\
                        docker build -t ${DOCKER_USER}/${IMAGE_NAME}:${IMAGE_VERSION} .
                        docker login docker.io -u ${DOCKER_USER} -p ${DOCKER_PASSWORD}
                        docker push ${DOCKER_USER}/${IMAGE_NAME}:${IMAGE_VERSION}
                        git tag ${IMAGE_VERSION}
                        git push https://$GITHUB_TOKEN@github.com/Bb4k/service.git ${IMAGE_VERSION}
                   """.stripIndent()
            }
        }
        stage('Run application') {
            steps{
                sh """\
                        IMAGE_VERSION=${IMAGE_VERSION} docker-compose up -d hello
                   """.stripIndent()
            }
        }
        stage('Test application') {
            steps {
                sh './gradlew testE2E'
            }
        }

    }
    post {
            always {
                deleteDir()
            }
    }
}
