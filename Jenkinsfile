pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'ecommerce-product-service'
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
                    cd ecommerce-product-service
                    chmod +x mvnw
                    ./mvnw clean package
                '''
            }
        }

        stage('Update Deployment YAML') {
            steps {
                script {
                    // Read pom.xml and extract the version using XmlSlurper
                    def pomContent = readFile('ecommerce-product-service/pom.xml')
                    def xml = new XmlSlurper().parseText(pomContent)
                    def version = xml.version.text().trim()
                    echo "Extracted version: ${version}"

                    // Define deployment YAML file path
                    def deploymentYaml = "ecommerce-parent/k8s/product-deployment.yaml"

                    // Update the image tag inside deployment YAML
                    sh "sed -i 's|image: registry.ecommerce.local:5000/ecommerce-product-service:latest|image: registry.ecommerce.local:5000/ecommerce-product-service:${version}|' ${deploymentYaml}"
                    echo "Updated ${deploymentYaml} with version ${version}"

                    // Commit and push changes to Git
                    sh """
                        cd ecommerce-parent
                        git add ${deploymentYaml}
                        git commit -m 'Update deployment to version ${version}'
                        git push origin main
                    """
                    echo "Changes successfully pushed to repository"
                }
            }
        }

    }

}