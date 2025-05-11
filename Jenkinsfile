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

                    // Define variables
                    def projectName = "ecommerce-product-service"
                    def parentProjectName = "ecommerce-parent"
                    def deploymentYaml_relative = "k8s/ecommerce/${projectName}-deployment.yaml"
                    def deploymentYaml_absolute = "${parentProjectName}/${deploymentYaml_relative}"

                    // Extract the version from pom.xml
                    def versionCommand = "xmllint --xpath \"/*[local-name()='project']/*[local-name()='version']/text()\" ${projectName}/pom.xml"
                    def version = sh(script: versionCommand, returnStdout: true).trim()
                    echo "Extracted version: ${version}"

                    // Update the image tag in the deployment YAML
                    def updateCommand = "sed -i 's|image: registry.ecommerce.local:5000/${projectName}:.*|image: registry.ecommerce.local:5000/${projectName}:${version}|' ${deploymentYaml_absolute}"

                    sh updateCommand
                    echo "Updated ${deploymentYaml_absolute} with version ${version}"

                    // Commit and push the changes to Git
                    sh """
                        cd ${parentProjectName}
                        git add ${deploymentYaml_relative}
                        git commit -m 'Update ${deploymentYaml_absolute} to version ${version}'
                        git push origin main
                    """
                    echo "Changes pushed to repository"
                }
            }
        }

    }

}