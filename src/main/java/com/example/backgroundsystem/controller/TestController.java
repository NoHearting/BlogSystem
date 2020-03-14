package com.example.backgroundsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {


    @GetMapping("markdown")
    public String markdown(Integer id,Map<String,Integer> map){
        map.put("id",id);
        return "test/testMarkDown";
    }

    @GetMapping("singleMarkdown")
    public String singleMarkdown(Integer ids,Map<String,Integer> map){
        if(null != ids){
            System.out.println(ids);
            map.put("id",ids);
        }else{
            System.out.println("没有id");
        }
        map.put("id",1);
        return "test/markdown";
    }

    @ResponseBody
    @RequestMapping("getData")
    public String getMarkdownData(Integer id){
        if(null!=id){
            System.out.println(id);
        }
        return new String("# 数据链路层\n" +
                "* 数据链路层适应的信道主要有以下两种类型：\n" +
                "  * **点对点通信**\n" +
                "  * **广播通信**\n" +
                "## 使用点对点信道的数据链路层\n" +
                "\n" +
                "### 数据链路和帧\n" +
                "* 所谓**链路**就是从一个节点**到相邻节点**的一段物理线路（有限或无线），而中间没有任何其他的交换节点。\n" +
                "* 当需要在一条线路上传送数据时，除了必须有一条物理线路外，还必须有一些必要的通信协议来控制这些数据的传输，若把实现这些协议的硬件和软件加到链路上，就构成了**数据链路**。\n" +
                "* 数据链路层把网络层交下来的数据构成帧发送到链路上，以及把接收到的帧中的数据取出并上交给网络层。\n" +
                "### 三个基本问题\n" +
                "* **封装成帧**\n" +
                "  + 封装成帧就是在一段数据的前后分别添加首部和尾部没这样就构成了一个帧。\n" +
                "  + 所有在互联网上传送的数据都以分组（即IP数据报）为传送单位。网络层的IP数据报传送到数据链路层就成为了帧的数据部分。\n" +
                "  + 在帧的数据部分的全面和后面分别添加上首部和尾部，就构成了一个完整的帧。首部和尾部的一个作用就是**帧定界**。\n" +
                "  + 每一种链路层协议都规定了所能传送的帧的数据部分长度上线——**最大传送单元MTU**\n" +
                "  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191224191156362.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxNzYyODYxNzk0,size_16,color_FFFFFF,t_70)\n" +
                "  + 控制字符**SOH**放在一帧的最前面，**EOT**放在一帧的最后面，最为**帧定界符**。\n" +
                "\n" +
                "* **透明传输**\n" +
                "  + 透明传输就是不管从键盘上输入什么字符都可以放在这样的帧中传输过去。**透明**表示**某一个实际存在的事物看起来却好像不存在一样**。在数据链路层透明的传送数据表示无论什么样的比特组合的数据，都能够按照原样没有差错地通过这个数据链路层。\n" +
                "  + 传输可能遇到的问题：\n" +
                "    - 当数据部分出现和帧定界符一样的字符时，数据链路层会错误的找到帧的边界，把部分帧收下，而把剩下的数据丢弃。这种传输就是**不透明的**。\n" +
                "  + 解决透明传输的问题：\n" +
                "    - **字节填充（字符填充）**\n" +
                "      发送端的数据链路层在数据中出现控制字符==SOH==或==EOT==的前面插入一个**转义字符**==ESC==。而在接收端的数据链路层在把数据送往网络层之前删除这个插入的转义字符。如果转义字符==ESC==也出现在数据中，那就在转义字符前面再插入一个转义字符。\n" +
                "\n" +
                "* **差错检测**\n" +
                "  + **比特差错**\n" +
                "  比特在传输过程中产生1变为0,0变为1的差错情况\n" +
                "  + **误码率BER**\n" +
                "  传输错误的比特占所传输比特总数的比特率。\n" +
                "  + **循环冗余检验CRC**\n" +
                "    - **原理**：假设传输数据M为k位，CRC就在数据后面添加供差错检测用的n位**冗余码**，然后构成一个帧发送出去，一共发送（k+n）位。\n" +
                "    - **n位冗余码的产生**：在M的后面添加n个0，得到的（k+n）位的数**除以**收发双方事先商定的长度为（n+1）的除数P（可知n大小是协商得出），得出商是Q而余数是R（n位），这个余数就作为冗余码拼接在数据M的后面发送出去。这种为了进行检错而添加的冗余码常称为**帧检验序列FCS**。\n" +
                "    - **通过CRC检验数据正确性**：在接收端把接收到的数据以帧为单位进行CRC检验，把收到的每一个帧都除以同样的除数P（模2运算，和异或运算类似），然后检查余数R。如果余数为0，则表示无差错，如果不等于0，则有差错。\n" +
                "    - 运算的例子：\n" +
                " ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191224191224541.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxNzYyODYxNzk0,size_16,color_FFFFFF,t_70)\n" +
                "\n" +
                "## 点对点协议PPP\n" +
                "### PPP协议的特点\n" +
                "* PPP协议是用户计算机和ISP进行通信时使用的数据链路层协议。\n" +
                "* **PPP协议应满足的需求**\n" +
                "  + 简单\n" +
                "  + 封装成帧\n" +
                "  + 透明性\n" +
                "  + 多种网络层协议\n" +
                "  + 多种类型链路\n" +
                "  + 差错检验\n" +
                "  + 检测连接状态\n" +
                "  + 最大传送单元\n" +
                "  + 网络层地址协商\n" +
                "  + 数据压缩协商\n" +
                "* **PPP协议的组成**\n" +
                "  + 一个将IP数据报封装到串行链路的方法。\n" +
                "  + 一个用来尽力、配置和测试数据链路连接的**链路控制协议**。\n" +
                "  + 一套**网络控制协议NCP**。\n" +
                "\n" +
                "### PPP协议的帧格式\n" +
                "* **各字段的意义**\n" +
                "  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191224191246477.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxNzYyODYxNzk0,size_16,color_FFFFFF,t_70)\n" +
                "* **字节填充**\n" +
                "  + **在异步传输时**，把转义字符定义为`0x7D`,并使用字节填充\n" +
                "  + 把信息字段中出现的每一个`0x7E`字节转变为2字节序列（`0x7D`,`0x5E`)\n" +
                "  + 若信息字段中出现一个`0x7D`的字节，则把`0x7D`转变为2字节序列（`0x7D`,`0x5D`)\n" +
                "  + 若信息中出现了ASCII码的控制字符（数值小于`0x20`），则在该字符前面要加入一个`0x7D`字节，同时改变该字符的编码。\n" +
                "* **零比特填充**\n" +
                "  + PPP协议在使用SONET/SDH链路时，使用**同步传输**，并使用领比特填充方法来实现透明传输。\n" +
                "  + 再发送端，先扫描整个信息字段，只要发现有五个连续的1，就立即填入一个0。\n" +
                "\n" +
                "### PPP协议的工作状态\n" +
                "\n" +
                "## 使用广播信道的数据链路层\n" +
                "### 局域网的数据链路层\n" +
                "* 局域网的最主要特点是：**网络为一个单位所拥有，且地理范围和站点数目均有限**\n" +
                "* 局域网的主要优点：\n" +
                "  + 具有广播功能，从一个站点可以很方便的访问全网。\n" +
                "  + 便于系统的扩展个逐渐演变，各设备的位置可灵活调整和改变。\n" +
                "  + 提高了系统的可靠性、可用性、和生存性。\n" +
                "* 局域可按网络拓扑进行分类：**星型网（a）**、**环形网（b）**、**总线网（c）**。\n" +
                " ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191224191309874.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxNzYyODYxNzk0,size_16,color_FFFFFF,t_70)\n" +
                "\n" +
                "* 共享信道要着重考虑的一个问题就是如何使众多用户能够合理而方便地共享通信媒体资源。在技术上有两种方法：\n" +
                "  + **静态划分信道** 如PDM，TDM，WDM，CDM等等\n" +
                "  + **动态媒体接入控制** 又称为**多点接入**，特点是信道并非在用户通信时固定分配给用户。这里又分为两类\n" +
                "    - **随机接入**  所有用户可随机地发送信息。\n" +
                "    - **受控接入**  用户不能随机地发送信息而必须服从一定的控制。\n" +
                "\n" +
                "* 适配器的作用\n" +
                "  + 进行数据的串并转换\n" +
                "  + 进行对数据帧的差错处理，帧错误就直接丢弃，正确就通知CPU\n" +
                "  + 存储计算机的硬件地址\n" +
                "### CSMA/CD协议（载波监听多点接入/碰撞检测协议）\n" +
                "* **总线的特点**：当一台计算机发送数据时，总线上的所有计算机都能够检测到这个数据。总线上只要有一台计算机在发送数据，总线的传输资源就被占用，所以**在同一时间只能允许一台计算机发送数据**。\n" +
                "\n" +
                "* 为了通信的简便，以太网采取了以下两种措施：\n" +
                "  + 采用较为灵活的**无连接**的工作方式，不必建立连接就可以直接发送数据。\n" +
                "    - 适配器对发送的数据帧不进行编号，也不要求对方发回确认\n" +
                "    - 提供的是不可靠的交付\n" +
                "    - 对有差错的帧是否重传由高层来决定\n" +
                "    - 采用**载波监听多点接入/碰撞检测CSMA/CD协议**\n" +
                "  + 以太网发送的数据都使用**曼彻斯特编码**的信号。\n" +
                "* CSMA/CD协议的要点\n" +
                "  + **多点接入** 说明这是总线型网络\n" +
                "  + **载波监听** 就是用电子技术检测总线上有没有其他计算机也在发送。\n" +
                "  + **碰撞检测** 也就是变发送变监听，即适配器变发送数据边检测信上的信号电压的变化情况，以便判断自己再发送数据时其他站是否也在发送数据。\n" +
                "* CSMA/CD的参数定义\n" +
                "  + 在局域网的分析中，常把总线上的**单程端到端传播时延**记做==$\\tau$==\n" +
                "  + 每一个站在自己发送数据之后的一小段时间之内，存在着遭遇碰撞的可能性，这一小段时间是不确定的，因此以太网不曹正某一时间之内一定能够把自己的数据帧成功地发送出去。\n" +
                "  + 以太网端到端的往返时间2$\\tau$称为**争用期**或**碰撞窗口**。发送的数据经过争用期还没有检测到碰撞，才能肯定这次发送不会碰撞。\n" +
                "  + 以太网使用**截断二进制指数退避**算法来确定碰撞重传的时机。\n" +
                "    - 算法让发生碰撞的站停止发送数据后，不是等待信道变为空闲后就立即再发送数据，而是**推迟**一个随机的时间。\n" +
                "    - 协议规定了基本退避时间为争用期2$\\tau$,具体的争用期时间是51.2us。退避的时间为一个随机数乘以基本退避时间。\n" +
                "  + 以太网规定最短帧长为**64字节**。凡长度小于64字节的帧都是由于冲突而异常中止的无效帧。\n" +
                "  + **强化碰撞** 当发送数据的站一旦发现了碰撞时，除了立即发送数据外，还要在继续发送32bit或者48bit的**人为干扰信号**，以便让所有的用户都知道现在已经发生了碰撞。\n" +
                "  + 以太网规定**帧间最小间隔**为9.6us，相当于96比特时间\n" +
                "* CSMA/CD协议工作的流程\n" +
                "  1. 准备发送：适配器重网络层获得一个分组，加上以太网的首部和尾部，组成以太网帧，放入适配器缓存中。在发送前，必须先**检测信道**\n" +
                "  2. 检测信道：若检测到信道忙，则应不听地检测，一直等待信道转变为空闲。若检测到信道空闲，并在96比特时间内信道保持空闲，就发送这个帧。\n" +
                "  3. 再发送过程中仍不停地检测信道，即网络适配器要变发送变监听。这里有两种可能性\n" +
                "      + 发送成功：在争用期一直未检测到碰撞。这个帧发送成功。\n" +
                "      + 发送失败：在争用期内检测到碰撞。这时立即停止发送数据，并按规定发送人为干扰信号。适配器接着执行指数退避算法，等待r倍512比特时间后，返回到步骤2，继续检测信道，若重传16次仍不能成功，则停止重传而向上报错。\n" +
                "\n" +
                "### 使用集线器的星型拓扑\n" +
                "* 传统以太网最初是使用粗同轴电缆，后来演进到使用比较便宜的细同轴电缆，最后发展为使用更便宜和更灵活的双绞线。这种以太网采用星型拓扑，在星型中央增加了一种可靠性非常高的设备，叫做**集线器**。\n" +
                "* 1990年IEEE制定出星型以太网`10BASE-T`的标准`802.3i`。10代表10 Mbits/s的数据率，BASE表示连接线上的信号是基带信号，T代表是双绞线。\n" +
                "* **集线器的特点**\n" +
                "  + 使用集线器的以太网在逻辑上仍是一个总线网，各站共享逻辑上的总线，使用的还是CSMA/CD协议。网络中的各站需竞争对传输媒体的控制，并且**在同一时刻之多只允许一个站发送数据**。\n" +
                "  + 一个集线器有许多**接口**，一个集线器很像一个**多接口的转发器**。\n" +
                "  + **集线器工作在物理层**，它的每个接口仅仅简单的转发比特，收到什么就转发什么。\n" +
                "  + 集线器采用了专门的芯片，进行自适应串音回波抵消。\n" +
                "\n" +
                "### 以太网的信道利用率\n" +
                " ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191224191342730.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxNzYyODYxNzk0,size_16,color_FFFFFF,t_70)\n" +
                "* 要提高以太网信道的利用率，就必须减小$\\tau$与$T_0$之比，在以太网中定义了参数$a$,它是以太网单程端到端时延$\\tau$与帧的发送时延$T_0$之比：\n" +
                "![在这里插入图片描述](https://img-blog.csdnimg.cn/2019122419135920.png)\n" +
                "\n" +
                "### 以太网的MAC层\n" +
                "* **MAC层的硬件地址**\n" +
                "  + 在局域我那个中，**硬件地址**又称为**物理地址**或**MAC地址**\n" +
                "  + 现在局域网适配器实际上使用的都属**6字节**的MAC地址。\n" +
                "* **MAC地址的组成**\n" +
                "  + 前3个字节称为**组织唯一标识符OUI**，通常也称为**公司标识符**。由厂家向向IEEE购买\n" +
                "  + 后3个字节则称为**扩展标识符**。由厂家自己指派。\n" +
                "  + IEEE规定第一字节的最低位为I/G位，为0时，地址字段表示一个**单个站地址**，为1时，表示**组地址**，用来进行**多播**。\n" +
                "* **适配器的过滤功能**\n" +
                "  + 适配器从网络上每收到一个MAC帧就先用硬件检查MAC帧中的目的地址，如果是发往本站的帧则收下，然后再进行其他的处理。否则就将此帧丢弃，不再进行其他处理。发往本站的帧包括：\n" +
                "    - **单播**帧（一对一），即收到的帧的MAC地址与本站的硬件地址相同。\n" +
                "    - **广播**帧（一对全体），即发送给本局域网上所有站点的帧。\n" +
                "    - **多播**帧（一对多），即发送给本局域网上一部分站点的帧。\n" +
                "* **MAC帧的格式**\n" +
                "![在这里插入图片描述](https://img-blog.csdnimg.cn/2019122419141956.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxNzYyODYxNzk0,size_16,color_FFFFFF,t_70)\n" +
                "\n" +
                "## 扩展的以太网\n" +
                "\n" +
                "### 在物理层扩展以太网\n" +
                "* 扩展主机和集线器之间的距离采用一种简单的方式就是使用**光纤**和一对光纤调制解调器。\n" +
                "* 使用多个集线器，就可以连接成覆盖更大范围的多级星型结构的以太网。\n" +
                "\n" +
                "### 在数据链路层扩展以太网\n" +
                "* **网桥** 对收到的帧根据其MAC帧的目的地址进行转发和过滤。\n" +
                "* 使用**交换式集线器**替换了网桥。交换式集线器常被称为以太网**交换机**或**第二层交换机**，强调这种交换机**工作在数据链路层**。\n" +
                "* **以太网交换机的特点**\n" +
                "  + 以太网交换机就是一个多接口网桥，一般都工作在全双工方式。\n" +
                "  + 具有并行性，能同时联通多对接口，使多对主机能同时通信。\n" +
                "  + 接口含有存储器，能在繁忙的时候存储到来的帧。\n" +
                "  + 即插即用，内部采用**帧交换表**，通过**自学习**算法主键简历起来。\n" +
                "  + 若对于10Mbit/s的以太网，才有10个接口的交换机则每个接口的带宽都是10Mbit/s，总的带宽为100Mbit/s，连接在交换机的每个用户的带宽都是10Mbit/s。\n" +
                "* **以太网交换机的自学习功能**\n" +
                "  + 自学习的流程\n" +
                "    - 当交换表为空时，此时接收到一个帧，查找交换表，如果没有查到应从哪个接口转发这个帧，就先把当前这个帧对应的主机信息和接口写入表中，然后广播给所有主机。\n" +
                "    - 当查找交换表中含有当前接收帧的目的地址应转发的接口时，就添加当前帧对应主机和接口信息到交换表，然后向查找到的接口广播消息。\n" +
                "![在这里插入图片描述](https://img-blog.csdnimg.cn/20191224191437337.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxNzYyODYxNzk0,size_16,color_FFFFFF,t_70)\n" +
                "  + **生成树协议**\n" +
                "    解决交换机自学习过程中的都圈子的现象。要点是不改变网络的时机拓扑，但在逻辑上切断某些链路，使得从一台主机到所有其他主机的路径是无环路的树桩结构。\n" +
                "\n" +
                "### 虚拟局域网\n" +
                "## 高速以太网\n" +
                "* 100BASE-T以太网\n" +
                "* 吉比特以太网\n" +
                "* 10吉比特以太网\n" +
                "* 使用以太网进行宽带接入\n");
    }
}