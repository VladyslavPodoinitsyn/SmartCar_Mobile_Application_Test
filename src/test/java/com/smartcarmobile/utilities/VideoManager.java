package com.smartcarmobile.utilities;

import io.appium.java_client.screenrecording.CanRecordScreen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class VideoManager {

    TestUtils utils = new TestUtils();

    public void startRecording() {
        utils.log().info("Starting screen recording...");
        CanRecordScreen recorder = (CanRecordScreen) new DriverManager().getDriver();
        recorder.startRecordingScreen();
    }

    public void stopRecording(String scenarioName) throws IOException {
        GlobalParams params = new GlobalParams();
        CanRecordScreen recorder = (CanRecordScreen) new DriverManager().getDriver();

        String media = recorder.stopRecordingScreen();
        String dirPath = params.getPlatformName() + "_"
                + params.getDeviceName() + File.separator + "Videos";

        File videoDir = new File(dirPath);

        synchronized (videoDir) {
            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }
        }

        File videoFile = new File(videoDir, scenarioName + ".mp4");
        try (FileOutputStream stream = new FileOutputStream(videoFile)) {
            byte[] decodedBytes = Base64.getDecoder().decode(media);
            stream.write(decodedBytes);
            utils.log().info("Video saved at: {}", videoFile.getAbsolutePath());
        } catch (Exception e) {
            utils.log().error("Error during video capture: {}", e.toString());
            throw new IOException("Failed to save video recording.", e);
        }

        // Re-encode video to ensure compatibility with QuickTime Player
        reencodeVideo(videoFile);

        // Additional logging to check the file details (optional)
        logVideoFileDetails(videoFile);
    }

    private void reencodeVideo(File videoFile) {
        try {
            String outputFilePath = videoFile.getAbsolutePath().replace(".mp4", "_reencoded.mp4");
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg", "-i", videoFile.getAbsolutePath(), "-vcodec", "h264", "-acodec", "aac", outputFilePath
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();

            // Rename the reencoded file to the original file name
            File reencodedFile = new File(outputFilePath);
            if (reencodedFile.exists()) {
                if (videoFile.delete()) {
                    reencodedFile.renameTo(videoFile);
                }
            }
            //utils.log().info("Re-encoded video saved at: {}", videoFile.getAbsolutePath());
        } catch (Exception e) {
            utils.log().error("Error during video re-encoding: {}", e.toString());
        }
    }

    private void logVideoFileDetails(File videoFile) {
        if (videoFile.exists()) {
            //utils.log().info("Video file exists: {}", videoFile.getAbsolutePath());
            //utils.log().info("File size: {} bytes", videoFile.length());
        } else {
            utils.log().warn("Video file does not exist: {}", videoFile.getAbsolutePath());
        }
    }
}
