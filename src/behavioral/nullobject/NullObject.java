package behavioral.nullobject;

public class NullObject {

    public static void main(String[] args) {

        DownloadManager downloadManager = new DownloadManager(new NullLog());
        downloadManager.download();
    }
}

interface Log {
    public void info(String info);
}

class ConsoleLog implements Log {

    @Override
    public void info(String info) {
        System.out.println(info);
    }
}

class NullLog implements Log {

    @Override
    public void info(String info) {

    }
}

class DownloadManager {

    private final Log log;

    DownloadManager(Log log) {
        this.log = log;
    }

    public void download() {

        log.info("Download realizado com sucesso");
    }

}
