local_maven_repo='C:\Users\dkask\IdeaProjects\camping-msa\common'

# Local Maven Repository의 snapshots 폴더로 deploy 실행
mvn -Dmaven.test.skip=true -DaltDeploymentRepository=snapshot-repo::default::file://${local_maven_repo}/snapshots clean deploy