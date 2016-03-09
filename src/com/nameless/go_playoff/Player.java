package com.nameless.go_playoff;


import java.io.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Player {

    enum PlayerTypes {
        BINARY,
        PYTHON,
        JAVA,
        HASKELL;

        private static HashMap<String, PlayerTypes> typeMap = new HashMap<>();

        static  {
            typeMap.put("exe", PlayerTypes.BINARY);
            typeMap.put("py", PlayerTypes.PYTHON);
            typeMap.put("java", PlayerTypes.JAVA);
            typeMap.put("hs", PlayerTypes.HASKELL);
        }

        public static PlayerTypes fromString(String str) {
            return typeMap.get(str);
        }
    }

    public class TimeoutException extends Exception {

    }

    private String name;
    private PlayerTypes type;

    private Process process;


    public Player(String name, String file_name) throws Exception {

        this.name = name;

        int dot_pos = file_name.lastIndexOf('.');
        if (dot_pos == -1)  {
            this.type = PlayerTypes.BINARY;
        } else  {
            this.type = PlayerTypes.fromString(file_name.substring(dot_pos + 1));
            if (this.type == null) {
                throw new Exception("Illegal file type");
            }
        }


        try {
            ProcessBuilder pb = null;

            switch (this.type) {
                case BINARY:
                    pb = new ProcessBuilder(file_name);
                    break;
                case PYTHON:
                    pb = new ProcessBuilder("python", file_name);
                    break;
                case JAVA:
                    pb = new ProcessBuilder("java", "-c", file_name);
                    break;
                case HASKELL:
                    pb = new ProcessBuilder("runhaskell", file_name);
                    break;
            }

            pb.directory(new File(System.getProperty("user.dir")));
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            this.process = pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean timedOut = false;

    public String run(String input) throws TimeoutException {
        try {
            Timer timeoutTimer  = new Timer();
            timeoutTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Player.this.killProcess();
                    System.out.println("Timeout!");
                    synchronized (Player.this) {
                        Player.this.timedOut = true;
                    }
                }
            }, 1000);

            input += "\n";
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            os.write(input);
            os.flush();

            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String result = br.readLine();

            timeoutTimer.cancel();
            synchronized (this) {
                if (timedOut || result == null) {
                    throw new TimeoutException();
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void killProcess() {
        this.process.destroyForcibly();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerTypes getType() {
        return type;
    }

    public void setType(PlayerTypes type) {
        this.type = type;
    }
}
