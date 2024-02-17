package ex03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

public class Downloader implements Runnable {
    private String myName;
    private List<String> URLs;
    private int thisLen;
    private static final String path = "/Users/letishal/goinfre/downloadFile_";
    private Menu menu;

    public Downloader(String myName, List<String> URLs, int thisLen, Menu menu) {
        this.myName = myName;
        this.URLs = URLs;
        this.thisLen = thisLen;
        this.menu = menu;
    }

    @Override
    public void run() {
        int urlsCount = getURLs().size();
        int portionSize = urlsCount / menu.getCountThreads();
        int startIndex = thisLen * portionSize;
        int endIndex = (thisLen + 1) * portionSize;
        for (int i = startIndex; i < endIndex; i++) {
            if (thisLen == menu.getCountThreads() - 1) {
                endIndex = urlsCount;
            }
            download(getURLs().get(i));
        }
    }

    private void download(String urlStr) {
        try {
            System.out.println("Thread-" + myName + " start download file number " + (getURLs().indexOf(urlStr) + 1));
            URL url = new URL(urlStr);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(path + urlStr.substring(urlStr.lastIndexOf('/') + 1));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            System.out.println("Thread-" + myName + " finish download file number " + (getURLs().indexOf(urlStr) + 1));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> getURLs() {
        return URLs;
    }

}
