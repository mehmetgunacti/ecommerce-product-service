pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'ecommerce-products'
    }
    stages {

        stage('Clone Repositories') {
            steps {
                sh 'rm -rf ecommerce-parent && git clone https://github.com/mehmetgunacti/ecommerce-parent.git'
                sh 'rm -rf ecommerce-product-service && git clone https://github.com/mehmetgunacti/ecommerce-product-service.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh '''
                    cd ecommerce-products
                    chmod +x mvnw
                    ./mvnw clean package
                '''
            }
        }

//         stage('Deploy to Kubernetes') {
//             steps {
//                 sh 'kubectl apply -f k8s-deployment.yaml'
//             }
//         }
//
//         stage('Verify') {
//             steps {
//                 sh 'kubectl rollout status deployment ecommerce-products -n ecommerce'
//             }
//         }

    }

}