项目框架搭建总结：
    1、建立Web工程
       * 导入需要的jar的包
         db:连接数据库的驱动包
         hibernate:使用hibernate的jar包
         jstl:java的标准标签库
         junit:测试用到的jar包
         spring:使用spring的jar包
         struts2:使用struts2的jar包
       * 项目体系分层：
         cn.itcast.elec.containner:自定义的spring容器，用于在控制层调用操作业务层
         cn.itcast.elec.dao:项目的dao层，负责连接数据库的操作
         cn.itcast.elec.daomain:封装实体对象（PO对象），对应连接数据库表的映射文件
         cn.itcast.elec.service:项目service层，负责操作各个功能模块的业务逻辑
         cn.itcast.elec.util:封装系统使用到的公用类的方法和属性
         cn.itcast.elec.web.action:系统控制层，负责页面和项目后台的跳转
         cn.itcast.elec.web.form:封装值对象（VO对象），对应页面传递的表单值的属性
         junit：测试类
         
       * 配置文件
          放置到src的目录下：
         beans.xml:spring的配置文件
         hibernate.cfg.xml：hibernate的配置文件
         struts.xml:struts2的配置文件  
    2、建立持久层
       * 在cn.itcast.elec.domain中创建持久层的对象ElecText.java
	         public class ElecText implements java.io.Serializable {
				private String textID;
				private String textName;
				private Date textDate;
				private String textRemark;
			 }
	   * 对应javabean的PO对象，创建于表的映射文件ElecText.hbm.xml文件
	        <hibernate-mapping>
				<class name="cn.itcast.elec.domain.ElecText" table="Elec_Text">
					<id name="textID" type="string">
						<column name="textID" sql-type="varchar(50)" not-null="true"/>
						<generator class="uuid"/>
					</id>
					<property name="textName" type="string">
						<column name="textName" sql-type="varchar(50)"/>
					</property>
					<property name="textDate" type="date">
						<column name="textDate" length="50"/>
					</property>
					<property name="textRemark" type="string">
						<column name="textRemark" sql-type="varchar(500)"/>
					</property>
				</class>
			</hibernate-mapping>
	   * 创建连接数据库的hibernate配置文件，hibernate.cfg.xml文件，放置到src目录下
	        <hibernate-configuration>
				<session-factory>
					<property name="hibernate.connection.username">root</property>
					<property name="hibernate.connection.password">root</property>
					<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
					<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/itcast1222elec</property>
					<property name="hibernate.connection.autocommit">true</property>
					
					<property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
					<property name="hibernate.hbm2ddl.auto">update</property>
					<property name="hibernate.show_sql">true</property>
					
					<mapping resource="cn/itcast/elec/domain/ElecText.hbm.xml"/>
				</session-factory>
			</hibernate-configuration>
	3、DAO层的搭建
	   * 在cn.itcast.elec.dao目录下，创建ICommonDao的接口
	        public interface ICommonDao<T> {
				public void save(T entity);
			}
	   * 在cn.itcast.elec.dao目录下，创建对ICommonDao的接口的实现类CommonDaoImpl
	        public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {
                public void save(T entity) {
					this.getHibernateTemplate().save(entity);
				}
				@Resource(name="sessionFactory")
				public final void setSessionFactoryDi(SessionFactory sessionFactory) {
					super.setSessionFactory(sessionFactory);
				}
			}
	   * 在cn.itcast.elec.dao.impl目录下，创建IelecTextDao的接口
	        public interface IElecTextDao extends ICommonDao<ElecText> {
				public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecTextDaoImpl";
			}
	   * 在cn.itcast.elec.dao.impl目录下，创建IelecTextDao接口的实现类ElecTextDaoImpl
	        @Repository(IElecTextDao.SERVICE_NAME)
			public class ElecTextDaoImpl extends CommonDaoImpl<ElecText> implements IElecTextDao {
				
			}
	   * 配置spring的配置文件beans.xml文件
	        <!-- 1:配置注解的自动扫描的范围 -->
			<context:component-scan base-package="cn.itcast.elec"></context:component-scan>
			<!-- 2:配置数据源 -->
			
			<!-- 3:创建sessionFactory，这是spring整合hibernate的入口 -->
			<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
				<property name="configLocation">
					<value>
						classpath:hibernate.cfg.xml
					</value>
				</property>
			</bean>
			<!-- 4:创建事务管理器 -->
			<bean id="txManage" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
				<property name="sessionFactory" ref="sessionFactory"></property>
			</bean>
			<!-- 5:以注解的形式管理事务 -->
			<tx:annotation-driven transaction-manager="txManage"/>
			
			</beans>
	4、建立Service层
	   * 在cn.itcast.elec.service目录下，创建IElecTextService的接口
	        public interface IElecTextService {
				public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecTextServiceImpl";
				public void saveElecText(ElecText elecText);
			}
	   * 在cn.itcast.elec.service.impl目录下，创建IElecTextService接口的实现类ElecTextServiceImpl
	        @Transactional(readOnly=true)
			@Service(IElecTextService.SERVICE_NAME)
			public class ElecTextServiceImpl implements IElecTextService {
				
				@Resource(name=IElecTextDao.SERVICE_NAME)
				private IElecTextDao elecTextDao;
				
				@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
				public void saveElecText(ElecText elecText){
					elecTextDao.save(elecText);
				}
			}
	5、建立控制（Action）层
	   * 导入系统需要的css、script、images的包
	      以及需要的jsp的包menu(系统登录、首页显示的jsp页面)、system(系统管理中的功能页面)
	   * 在cn.itcast.elec.web.action创建ElecTextAction
	        @SuppressWarnings("serial")
			public class ElecTextAction extends BaseAction implements ModelDriven<ElecTextForm>{
			
				private IElecTextService elecTextService = (IElecTextService)ServiceProvider.getService(IElecTextService.SERVICE_NAME);
				
				private ElecTextForm elecTextForm = new ElecTextForm();
				
				public ElecTextForm getModel() {
					return elecTextForm;
				}
				
				public String save(){
					ElecText elecText = new ElecText();
					elecText.setTextName(elecTextForm.getTextName());//测试名称
					elecText.setTextDate(StringHelper.stringConvertDate(elecTextForm.getTextDate()));//测试日期
					elecText.setTextRemark(elecTextForm.getTextRemark());//测试备注
					elecTextService.saveElecText(elecText);
					return "save";
				}
			}
	   * 在cn.itcast.elec.web.form创建ElecTextForm(值对象)
	        public class ElecTextForm implements java.io.Serializable {
				private String textID;
				private String textName;
				private String textDate;
				private String textRemark;
			}
	   * 在cn.itcast.elec.web.action创建BaseAction类
	        @SuppressWarnings("serial")
			public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
			    @SuppressWarnings("unused")
				protected HttpServletRequest request = null;
				@SuppressWarnings("unused")
				protected HttpServletResponse response = null;
			    public void setServletRequest(HttpServletRequest request) {
					this.request = request;	
				}
				public void setServletResponse(HttpServletResponse response) {
					this.response = response;	
				}
			}
	   * 自定义spring的容器
	        * 在cn.itcast.elec.container包下，建立ServiceProviderCord类
	            public class ServiceProviderCord {
					protected static ApplicationContext ac;
					public static void load(String filename){
						ac = new ClassPathXmlApplicationContext(filename);
					}
				}
			* 在cn.itcast.elec.container包下，建立ServiceProvider类
			    public class ServiceProvider {
					public static ServiceProviderCord spc;
					static{
						spc = new ServiceProviderCord();
						spc.load("beans.xml");
					}
					public static Object getService(String serviceName){
						if(StringUtils.isBlank(serviceName)){
							throw new RuntimeException("当前服务名称不存在");
						}
						Object object = null;
						if(spc.ac.containsBean(serviceName)){
							object = spc.ac.getBean(serviceName);
						}
						if(object==null){
							throw new RuntimeException("当前服务名称【"+serviceName+"】下的服务节点不存在");
						}
						return object;
					}
				}
		 * 建立struts的配置，放置到src的目录下
			   <struts>
					<!-- 配置action的访问路径为.do的形式 -->
					<constant name="struts.action.extension" value="do"></constant>
					<!-- 配置struts的开发模式 -->
					<constant name="struts.devMode" value="true"></constant>
					<!-- 配置struts的简单模式 -->
					<constant name="struts.ui.theme" value="simple"></constant>
					<package name="system" namespace="/system" extends="struts-default">
						<action name="elecTextAction_*" class="cn.itcast.elec.web.action.ElecTextAction" method="{1}">
							<result name="save">
								/system/textAdd.jsp
							</result>
						</action>
					</package>
				</struts>
			同时在web.xml中添加struts的过滤器
			  <filter>
			  	<filter-name>struts2</filter-name>
			  	<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
			  </filter>
			  <filter-mapping>
			  	<filter-name>struts2</filter-name>
			  	<url-pattern>/*</url-pattern>
			  </filter-mapping>
	
         