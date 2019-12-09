package com.xiaoxiao.utils;


import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import java.util.*;

/**
 * Created by limi on 2017/10/22.
 */
public class MarkdownUtils {

    /**
     * markdown格式转换成HTML格式
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
       /* Parser parser = Parser.builder().build();*/
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /**
     * 增加扩展[标题锚点，表格生成]
     * Markdown转换成HTML
     * @param markdown
     * @return
     */
    public static String markdownToHtmlExtensions(String markdown) {
        //h标题生成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的HTML
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /**
     * 处理标签的属性
     */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            //改变a标签的target属性为_blank
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }
    }


    public static void main(String[] args) {
        String table = "| hello | hi   | 哈哈哈   |\n" +
                "| ----- | ---- | ----- |\n" +
                "| 斯维尔多  | 士大夫  | f啊    |\n" +
                "| 阿什顿发  | 非固定杆 | 撒阿什顿发 |\n" +
                "\n";
        String a = "算法描述：\n" + "雪花算法是一款开源的ID算法。主要的组成：\n" + "最高位是符号位，始终为0，不可用。\n" + "41位的时间序列，精确到毫秒级，41位的长度可以使用69年。时间位还有一个很重要的作用是可以根据时间进行排序。\n" + "10位的机器标识，10位的长度最多支持部署1024个节点。\n" + "12位的计数序列号，序列号即一系列的自增id，可以支持同一节点同一毫秒生成多个ID序号，12位的计数序列号支持每个节点每毫秒产生4096个ID序号\n" + "\n" + "![](https://xiaoxiao-xiaoxiao.oss-cn-beijing.aliyuncs.com/xiaoxiao-and-me/5858de0ebcf54688b40392cf605694c1.jpg)\n" + "\n" + "```java\n" + "package com.xiaoxiao.utils;\n" + "\n" + "import java.lang.management.ManagementFactory;\n" + "import java.net.InetAddress;\n" + "import java.net.NetworkInterface;\n" + "\n" + "/**\n" + " * <p>鍚嶇О锛欼dWorker.java</p>\n" + " * <p>鎻忚堪锛氬垎甯冨紡鑷\uE044\uE583闀縄D</p>\n" + " * <pre>\n" + " *     Twitter鐨� Snowflake銆�JAVA瀹炵幇鏂规\uE50D\n" + " * </pre>\n" + " * 鏍稿績浠ｇ爜涓哄叾IdWorker杩欎釜绫诲疄鐜帮紝鍏跺師鐞嗙粨鏋勫\uE6E7涓嬶紝鎴戝垎鍒\uE0A4敤涓�涓�0琛ㄧず涓�浣嶏紝鐢ㄢ�斿垎鍓插紑閮ㄥ垎鐨勪綔鐢\uE7D2細\n" + " * 1||0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000\n" + " * 鍦ㄤ笂闈㈢殑瀛楃\uE0C1涓蹭腑锛岀\uE0C7涓�浣嶄负鏈\uE043娇鐢\uE7D2紙瀹為檯涓婁篃鍙\uE219綔涓簂ong鐨勭\uE0C1鍙蜂綅锛夛紝鎺ヤ笅鏉ョ殑41浣嶄负姣\uE0A4\uE757绾ф椂闂达紝\n" + " * 鐒跺悗5浣峝atacenter鏍囪瘑浣嶏紝5浣嶆満鍣↖D锛堝苟涓嶇畻鏍囪瘑绗︼紝瀹為檯鏄\uE219负绾跨▼鏍囪瘑锛夛紝\n" + " * 鐒跺悗12浣嶈\uE1DA姣\uE0A4\uE757鍐呯殑褰撳墠姣\uE0A4\uE757鍐呯殑璁℃暟锛屽姞璧锋潵鍒氬ソ64浣嶏紝涓轰竴涓狶ong鍨嬨��\n" + " * 杩欐牱鐨勫ソ澶勬槸锛屾暣浣撲笂鎸夌収鏃堕棿鑷\uE044\uE583鎺掑簭锛屽苟涓旀暣涓\uE044垎甯冨紡绯荤粺鍐呬笉浼氫骇鐢烮D纰版挒锛堢敱datacenter鍜屾満鍣↖D浣滃尯鍒嗭級锛�\n" + " * 骞朵笖鏁堢巼杈冮珮锛岀粡娴嬭瘯锛宻nowflake姣忕\uE757鑳藉\uE644浜х敓26涓嘔D宸﹀彸锛屽畬鍏ㄦ弧瓒抽渶瑕併��\n" + " * <p>\n" + " * 64浣岻D (42(姣\uE0A4\uE757)+5(鏈哄櫒ID)+5(涓氬姟缂栫爜)+12(閲嶅\uE632绱\uE21A姞))\n" + " *闆\uE047姳绠楁硶ID鐢熸垚鍣�\n" + " * @author Polim\n" + " */\n" + "public class IdWorker\n" + "{\n" + "    // 鏃堕棿璧峰\uE750鏍囪\uE187鐐癸紝浣滀负鍩哄噯锛屼竴鑸\uE100彇绯荤粺鐨勬渶杩戞椂闂达紙涓�鏃︾‘瀹氫笉鑳藉彉鍔\uE7D2級\n" + "    private final static long twepoch = 1288834974657L;\n" + "    // 鏈哄櫒鏍囪瘑浣嶆暟\n" + "    private final static long workerIdBits = 5L;\n" + "    // 鏁版嵁涓\uE15E績鏍囪瘑浣嶆暟\n" + "    private final static long datacenterIdBits = 5L;\n" + "    // 鏈哄櫒ID鏈�澶у��\n" + "    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);\n" + "    // 鏁版嵁涓\uE15E績ID鏈�澶у��\n" + "    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);\n" + "    // 姣\uE0A4\uE757鍐呰嚜澧炰綅\n" + "    private final static long sequenceBits = 12L;\n" + "    // 鏈哄櫒ID鍋忓乏绉�12浣�\n" + "    private final static long workerIdShift = sequenceBits;\n" + "    // 鏁版嵁涓\uE15E績ID宸︾Щ17浣�\n" + "    private final static long datacenterIdShift = sequenceBits + workerIdBits;\n" + "    // 鏃堕棿姣\uE0A4\uE757宸︾Щ22浣�\n" + "    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;\n" + "\n" + "    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);\n" + "    /* 涓婃\uE0BC鐢熶骇id鏃堕棿鎴� */\n" + "    private static long lastTimestamp = -1L;\n" + "    // 0锛屽苟鍙戞帶鍒�\n" + "    private long sequence = 0L;\n" + "\n" + "    private final long workerId;\n" + "    // 鏁版嵁鏍囪瘑id閮ㄥ垎\n" + "    private final long datacenterId;\n" + "\n" + "    public IdWorker()\n" + "    {\n" + "        this.datacenterId = getDatacenterId(maxDatacenterId);\n" + "        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);\n" + "    }\n" + "\n" + "    /**\n" + "     * @param workerId     宸ヤ綔鏈哄櫒ID\n" + "     * @param datacenterId 搴忓垪鍙�\n" + "     */\n" + "    public IdWorker(long workerId, long datacenterId)\n" + "    {\n" + "        if (workerId > maxWorkerId || workerId < 0)\n" + "        {\n" + "            throw new IllegalArgumentException(String.format(\"worker Id can't be greater than %d or less than 0\", maxWorkerId));\n" + "        }\n" + "        if (datacenterId > maxDatacenterId || datacenterId < 0)\n" + "        {\n" + "            throw new IllegalArgumentException(String.format(\"datacenter Id can't be greater than %d or less than 0\", maxDatacenterId));\n" + "        }\n" + "        this.workerId = workerId;\n" + "        this.datacenterId = datacenterId;\n" + "    }\n" + "\n" + "    /**\n" + "     * 鑾峰彇涓嬩竴涓狪D\n" + "     *\n" + "     * @return\n" + "     */\n" + "    public synchronized long nextId()\n" + "    {\n" + "        long timestamp = timeGen();\n" + "        if (timestamp < lastTimestamp)\n" + "        {\n" + "            throw new RuntimeException(String.format(\"Clock moved backwards.  Refusing to generate id for %d milliseconds\", lastTimestamp - timestamp));\n" + "        }\n" + "\n" + "        if (lastTimestamp == timestamp)\n" + "        {\n" + "            // 褰撳墠姣\uE0A4\uE757鍐咃紝鍒�+1\n" + "            sequence = (sequence + 1) & sequenceMask;\n" + "            if (sequence == 0)\n" + "            {\n" + "                // 褰撳墠姣\uE0A4\uE757鍐呰\uE178鏁版弧浜嗭紝鍒欑瓑寰呬笅涓�绉�\n" + "                timestamp = tilNextMillis(lastTimestamp);\n" + "            }\n" + "        }\n" + "        else\n" + "        {\n" + "            sequence = 0L;\n" + "        }\n" + "        lastTimestamp = timestamp;\n" + "        // ID鍋忕Щ缁勫悎鐢熸垚鏈�缁堢殑ID锛屽苟杩斿洖ID\n" + "        long nextId = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;\n" + "\n" + "        return nextId;\n" + "    }\n" + "\n" + "    private long tilNextMillis(final long lastTimestamp)\n" + "    {\n" + "        long timestamp = this.timeGen();\n" + "        while (timestamp <= lastTimestamp)\n" + "        {\n" + "            timestamp = this.timeGen();\n" + "        }\n" + "        return timestamp;\n" + "    }\n" + "\n" + "    private long timeGen()\n" + "    {\n" + "        return System.currentTimeMillis();\n" + "    }\n" + "\n" + "    /**\n" + "     * <p>\n" + "     * 鑾峰彇 maxWorkerId\n" + "     * </p>\n" + "     */\n" + "    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId)\n" + "    {\n" + "        StringBuffer mpid = new StringBuffer();\n" + "        mpid.append(datacenterId);\n" + "        String name = ManagementFactory.getRuntimeMXBean().getName();\n" + "        if (!name.isEmpty())\n" + "        {\n" + "            /*\n" + "             * GET jvmPid\n" + "             */\n" + "            mpid.append(name.split(\"@\")[0]);\n" + "        }\n" + "        /*\n" + "         * MAC + PID 鐨� hashcode 鑾峰彇16涓\uE043綆浣�\n" + "         */\n" + "        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);\n" + "    }\n" + "\n" + "    /**\n" + "     * <p>\n" + "     * 鏁版嵁鏍囪瘑id閮ㄥ垎\n" + "     * </p>\n" + "     */\n" + "    protected static long getDatacenterId(long maxDatacenterId)\n" + "    {\n" + "        long id = 0L;\n" + "        try\n" + "        {\n" + "            InetAddress ip = InetAddress.getLocalHost();\n" + "            NetworkInterface network = NetworkInterface.getByInetAddress(ip);\n" + "            if (network == null)\n" + "            {\n" + "                id = 1L;\n" + "            }\n" + "            else\n" + "            {\n" + "                byte[] mac = network.getHardwareAddress();\n" + "                id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;\n" + "                id = id % (maxDatacenterId + 1);\n" + "            }\n" + "        } catch (Exception e)\n" + "        {\n" + "            System.out.println(\" getDatacenterId: \" + e.getMessage());\n" + "        }\n" + "        return id;\n" + "    }\n" + "}\n" + "```\n";
        System.out.println(markdownToHtmlExtensions(a));
    }
}
