import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author F3ver1
 * @date 2018/11/27 19:34
 */
public class DownloadDemo {
    public static void main(String[] args) throws Exception {
        DownloadDemo demo = new DownloadDemo();
        String HTML = demo.getHTML();
        System.out.println(HTML);
        List<String> list = demo.getImgLabel(HTML);
        System.out.println(list);
        List<String> list1 = demo.getImgSrc(list);
        System.out.println(list1);
        demo.Download(list1);

    }

    /**
     * @return java.lang.String
     * @author Fever1
     * @Description 获取目标网页的HTML代码
     * @Date 20:19 2018/11/27
     * @Param []
     **/
    private String getHTML() throws IOException {
        HttpURLConnection conn = (HttpURLConnection)
                new URL("https://tieba.baidu.com/p/5954643505").openConnection();
        InputStream in = conn.getInputStream();
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        String string = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((string = buf.readLine()) != null) {
            stringBuffer.append(string, 0, string.length());
            stringBuffer.append('\n');
        }
        in.close();
        buf.close();
        return stringBuffer.toString();
    }

    /**
     * @return java.util.List<java.lang.String>
     * @author Fever1
     * @Description 获取该网页的图片标签
     * @Date 20:22 2018/11/27
     * @Param [HTML]
     **/
    private List<String> getImgLabel(String HTML) {
        String regex = "<img.*src=(.*?)[^>]*?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(HTML);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * @return java.util.List<java.lang.String>
     * @author Fever1
     * @Description 获取图片的下载地址
     * @Date 22:29 2018/11/27
     * @Param [list1]
     **/
    private List<String> getImgSrc(List<String> list1) {
        String regex = "https+://[^\\s]*.jpg";
        List<String> list = new ArrayList<>();
        for (String str : list1) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                list.add(matcher.group());
            }
        }
        return list;
    }

    /**
     * @return void
     * @author Fever1
     * @Description 下载图片
     * @Date 22:29 2018/11/27
     * @Param [list2]
     **/
    private void Download(List<String> list2) throws IOException {
        int k = 0;
        for (String str : list2) {
            HttpURLConnection conn = (HttpURLConnection)
                    new URL(str).openConnection();
            String imageName = str.substring(str.lastIndexOf("/") + 1, str.length());
            InputStream in = conn.getInputStream();
            k++;
            OutputStream out = new FileOutputStream(new File("D:\\图片\\" + k + ".jpg"));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read()) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        }

    }
}

