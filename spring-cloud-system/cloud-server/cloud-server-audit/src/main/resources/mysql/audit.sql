
/*==============================================================*/
/* Table: AUDIT_LOGS                                            */
/*==============================================================*/
create table AUDIT_LOGS
(
    ID                   varchar(32) not null,
    TYPE                 varchar(32) comment '日志来源类型',
    CATEGORY_NAME        varchar(256) comment '日志分类名称',
    CATEGORY_ID          varchar(32) comment '日志分类ID',
    APP_NAME             varchar(256) comment '应用名称',
    APP_CODE             varchar(32) comment '应用编码',
    APP_NODE_IP          varchar(64) comment '应用节点IP地址',
    USER_NAME            varchar(64) comment '用户名称，帐号',
    USER_IP              varchar(64) comment '用户IP地址',
    THEAD_NAME           varchar(128) comment '线程名称',
    CLASS_NAME           varchar(128) comment '类名',
    METHOD_NAME          varchar(64) comment '方法名',
    REQUEST_URL          varchar(1024) comment '请求URL地址',
    START_TIME           datetime default NULL comment '开始时间',
    END_TIME             datetime default NULL comment '结束时间',
    COST_TIME            int default NULL comment '执行时间',
    INPUT                text comment '输入参数',
    OUTPUT               text comment '输出数据',
    MESSAGE_TEXT         text comment '日志文本消息',
    EXCEPTION_TRACE      text comment '异常消息',
    COMPUTE_COST_TIME    int default NULL comment '日志分析执行时间',
    EVENT_LEVEL          varchar(22) comment '日志事件等级',
    OPERATION_NAME       varchar(64) comment '操作名称',
    SUCCESS_CODE         varchar(8) comment '成功编码',
    EVENT_TYPE           varchar(8) comment '事件类型',
    ANALYSIS_ENABLE      varchar(8) comment '是否图标分析',
    DEV_ID               varchar(36),
    primary key (ID)
);
alter table AUDIT_LOGS comment '日志审计表';
