package com.kivimango.core.rpfountain.impl;

import com.kivimango.core.rpfountain.Replay;
import com.kivimango.core.rpfountain.ReplayService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class ReplayServiceImpl implements ReplayService {

    private final Path replayDir;
    private final Logger log = Logger.getLogger("ReplayServiceImpl");

    public ReplayServiceImpl(Path replayDir) {
        Objects.requireNonNull(replayDir, "ERROR: Cannot acquire replay directory!");
        this.replayDir = replayDir;
        log.fine("The replay directory is " + replayDir);
    }

    @Override
    public List<Replay> listLocal() throws IOException {
        List<Replay> replays = new ArrayList<>();
        Stream<Path> stream = Files.list(replayDir).filter(path -> path.toFile().isFile()).filter(path -> path.toString().endsWith(".dem"));

        stream.forEach(path -> {
            try {
                Replay replay = new Replay.Builder()
                        .withFilename(path.getFileName().toString())
                        .withMatchId(path.getFileName().toString())
                        .withPlayedAt(Files.getLastModifiedTime(path).toString())
                        .withFileSzie(Files.size(path))
                        .build();
                replays.add(replay);
            } catch (IOException e) {
                log.warning("Error during listing replays : " + e);
            }
        });
        stream.close();
        return replays;
    }

    @Override
    public Long countReplays() throws IOException {
        try(Stream<Path> stream = Files.walk(replayDir)) {
            return stream.filter(path -> path.toFile().isFile()).filter(path -> path.toString().endsWith(".dem")).count();
        }
    }

    @Override
    public Long size() throws IOException {
        return Files.walk(replayDir).filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length()).sum();
    }

}
