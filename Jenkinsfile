library identifier: 'BattlePipelineLib@master', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://github.com/BattlePlugins/BattlePipelineLib',
     credentialsId: 'github-login'])

compileAndDeployMaven {
    repo = 'BattleMcAPI'
    target_path = 'modules/BattleMcAPI/'
}