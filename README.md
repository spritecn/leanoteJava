# leanoteJava
java + DBMS的leanote后端

# 愿景
- 支持sqlite,mysql,postgreSql 等关系数据库，降低布署和数据理备份处理难度
- 使用java开发，便于多人合作（java群体相对多一点)
- KISS，简单易理解，易布署
- 客户端支持蚂蚊笔记原有的windows,linux,apk,ios

# 技术选型
- java8
- sparkJava,sql2o
- that's all

# 进度
- 为方便调试开发期间sqlite
- [ ] 需求分析 和任务同步
- [x] 基础框架搭建 21/3/4 
- [x] 用户及登录  21/3/8
- [ ] 文章分类/标签 待定
- [ ] 笔记保存 待定
- [ ] 笔记同步 待定
- [ ] 搜索  待定
- [ ] mysql/postgresql支持 待定
- [ ] 腾讯云函数 + 云api 支持 待定 

# Feature
- **等你来提issues**
- 启动时如果数据库没有表，初始化表并添加初始用户
- 数据库配置/端口 支持ENV
- dock 支持

# 食用
- idea 或其他支持gradle的工具拉取项目
- 编译
- 传数据库连接参数，启动 start/Appliction
- sqlite传参如：jdbc:sqlite:d:/sqlite/leanote.db?journal_mode=wal&synchronous=off
- 数据库结构参见 https://github.com/spritecn/leanoteJava/blob/main/doc/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1.md


