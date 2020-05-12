package com.example.backgroundsystem;

import com.example.backgroundsystem.utils.HtmlToPlainText;
import com.example.backgroundsystem.utils.MarkdownToHtml;
import org.junit.jupiter.api.Test;

public class MarkdownToPlainTextTest {

    @Test
    public void testConvert(){
        String html = MarkdownToHtml.convert("# Linux网络编程基础API\n" +
                "\n" +
                "## 主机字节序和网络字节序\n" +
                "\n" +
                "现代CPU的累加器一次都能装载(至少)4字节(这里考虑32位机),即一个整数.那么这4字节在内存中排列的顺序将影响它被累加器装载成的整数的值.\n" +
                "\n" +
                "### 1. 字节序的分类\n" +
                "\n" +
                "* **大端字节序**(big endian)\n" +
                "\n" +
                "  大端字节序是指一个整数的高位字节(23~31bit)存储在内存的低地址处,低位字节(0~7bit)存储在内存的高地址处.\n" +
                "\n" +
                "* **小端字节序**(little endian)\n" +
                "\n" +
                "  小端字节序则是指整数的高位字节存储在内存的高地址处,而低位字节则存储在内存的低地址处.\n" +
                "\n" +
                "### 2. 主机字节序和网络字节序\n" +
                "\n" +
                "* 现代PC大多采用**小端字节序**,因此小端字节序又被称为**网络字节序**\n" +
                "* 现在网络传输中的字节序都采用**大端字节序**,所以大端字节序又被称为**网络字节序**\n");
        System.out.println(html);
        String text = HtmlToPlainText.convert(html);
        System.out.println(text);
    }
}
