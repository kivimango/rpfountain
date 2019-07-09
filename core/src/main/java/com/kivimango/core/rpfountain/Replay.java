package com.kivimango.core.rpfountain;

public final class Replay {
    private String fileName, matchId, playedAt;
    private Long fileSize = 0L;

    public String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMatchId() {
        return matchId;
    }

    private void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    private void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getPlayedAt() {
        return playedAt;
    }

    private void setPlayedAt(String playedAt) {
        this.playedAt = playedAt;
    }

    @Override
    public String toString() {
        return "Replay{" +
                "fileName='" + fileName + '\'' +
                ", matchId='" + matchId + '\'' +
                ", playedAt='" + playedAt + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }

    public static final class Builder {
        private static final String NO_DATA ="N/A";
        private String fileName, matchId, playedAt;
        private Long fileSize;

        public Builder() {
        }

        public Builder withFilename(String fName) {
            if (fName == null || fName.isEmpty())
                this.fileName = NO_DATA;
            this.fileName = fName;
            return this;
        }

        public Builder withMatchId(String matchId) {
            if(matchId == null || matchId.isEmpty())
                this.matchId = NO_DATA;
            this.matchId = matchId;
            return this;
        }

        public Builder withPlayedAt(String date) {
            if(date == null || date.isEmpty())
                this.playedAt = NO_DATA;
            this.playedAt = date;
            return this;
        }

        public Builder withFileSzie(Long fs) {
            if(fs < 0)
                this.fileSize = 0L;
            this.fileSize = fs;
            return this;
        }

        public Replay build() {
            Replay replay = new Replay();
            replay.setFileName(this.fileName);
            replay.setMatchId(this.matchId);
            replay.setPlayedAt(this.playedAt);
            replay.setFileSize(this.fileSize);
            return replay;
        }
    }
}
