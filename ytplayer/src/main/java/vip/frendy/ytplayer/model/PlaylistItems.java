package vip.frendy.ytplayer.model;

/**
 * Created by frendy on 2017/11/14.
 */

public class PlaylistItems {

    /**
     * kind : youtube#playlistItem
     * etag : "ld9biNPKjAjgjV7EZ4EKeEGrhao/Ju09PU6Qr7g-TYckDt5z5GI1Kbw"
     * id : UExGZ3F1TG5MNTlhbU45dFlyN28yYTYweUZVZnpRTzNzVS42ODg5M0ZFRUNFNkM2QTlD
     * snippet : {"publishedAt":"2017-11-14T08:33:36.000Z","channelId":"UC-9-kyTW8ZkZNDHQJ6FgpwQ","title":"方泂鑌 A-Bin【最笨的人是我 The Fool】特別演出：嚴正嵐、張耀仁 HD 高清官方完整版 MV","description":"● 方泂鑌「我不是神」LIVE 演唱會 購票資訊！！！ \n(趕緊來搶票～～)\n\n日期：2017/11/25(六)19:30  \n地點：三創生活園區 Clapper Studio\n售票：udn售票網 https://goo.gl/T1gmTU\n\n哈雷彗星是著名的短周期彗星，每隔 75-76 年就能從地球上看見，是唯⼀能用裸眼直接從地球看見的短週期彗星，⼈的一生中，僅能經歷 1-2 次遇見他的來訪。\n\n    〈最笨的人是我〉MV 邀請了新銳導演 - 胡瑞財執導，以魔幻寫實的敘事手法，運用「彗星」的巧妙奇幻寓意，連結角色情感。導演藉由視覺傳達隱晦的愛情觀與淡淡遺憾。 夢中夢的開放式結局，片尾究竟是破鏡重圓，亦或僅是女孩（男孩）各自心中的美好幻象，就留給觀眾去揣測想像。\n\n而 MV 的高反差光影、寫實場域氛圍，點綴細緻雕琢特效，結合「手持攝影」的呼吸感與特寫捕捉，穿插「情緒高點」之高格畫面，如：淚水的滑落，慢動作渲染影像情緒，都時時刻刻揪著每個人的心。\n\n這支 MV 的精采的戲劇部分，邀請到因《花甲男孩轉大人》走紅的嚴正嵐與植劇場優質演員 - 張耀仁在 MV 中飾演一對即將要分手的戀人。\n\n兩人在 MV 中大飆演技，A-Bin 在 MV 中，成為一日彗星的凡間化身(如同來自外星的都教授)，三人穿插在這充滿奇幻的 MV 中精采可期。\n\n��方泂鑌 Abin《我不是神》專輯數位聆聽��\nhttps://lnk.to/Abin_ImNotGod \n\n最笨的人是我\n作詞：吳易緯 \n作曲：方泂鑌\n編曲：方泂鑌\nMV 導演：胡瑞財\n\n若時光真的能倒流  彌補犯的錯\n請再次遇見我  \n能做我都做  該說的都說破  \n把傷害    讓我全部回收\n\n若  沒有狠狠地傷透 人不會想通  \n趁早學會坦白  就不用愧疚\n早點參透溫柔  \n就不會 又讓你  淚流\n\n時間讓堅強  脆弱\n最笨的人頑固  爭愛的對錯\n原來那個人是我（最笨的人是我）\n\n都怪我  軟弱  偽裝  逞強  放開你的手\n都怪我  直到 錯過  才懂  千真萬確愛過\n多希望時間能倒流  會再有以後\n再給我 一秒鐘  再回到  你心中\n\n我願用  每年  每天  每分  把錯都認過\n我願用  餘生  與你  相擁  不再讓你走\n若一切再從頭  不會再放開手\n請給我   再一次  愛著妳 不保留\n\n【方泂鑌 Abin官方社群】\n官方粉絲團 https://www.facebook.com/iamAbin \n官方微博https://goo.gl/6KVpm6\n海蝶音樂/太合音樂YouTube官方頻道 https://www.youtube.com/user/OceanButterfliesUS \n海蝶音樂粉絲團https://www.facebook.com/pg/OBMusic/posts/","thumbnails":{"default":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/default.jpg","width":120,"height":90},"medium":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/mqdefault.jpg","width":320,"height":180},"high":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/hqdefault.jpg","width":480,"height":360},"standard":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/sddefault.jpg","width":640,"height":480},"maxres":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/maxresdefault.jpg","width":1280,"height":720}},"channelTitle":"Music","playlistId":"PLFgquLnL59amN9tYr7o2a60yFUfzQO3sU","position":0,"resourceId":{"kind":"youtube#video","videoId":"H6SShCF58-U"}}
     */

    private String kind;
    private String etag;
    private String id;
    private SnippetBean snippet;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SnippetBean getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetBean snippet) {
        this.snippet = snippet;
    }

    public static class SnippetBean {
        /**
         * publishedAt : 2017-11-14T08:33:36.000Z
         * channelId : UC-9-kyTW8ZkZNDHQJ6FgpwQ
         * title : 方泂鑌 A-Bin【最笨的人是我 The Fool】特別演出：嚴正嵐、張耀仁 HD 高清官方完整版 MV
         * description : ● 方泂鑌「我不是神」LIVE 演唱會 購票資訊！！！
         * thumbnails : {"default":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/default.jpg","width":120,"height":90},"medium":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/mqdefault.jpg","width":320,"height":180},"high":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/hqdefault.jpg","width":480,"height":360},"standard":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/sddefault.jpg","width":640,"height":480},"maxres":{"url":"https://i.ytimg.com/vi/H6SShCF58-U/maxresdefault.jpg","width":1280,"height":720}}
         * channelTitle : Music
         * playlistId : PLFgquLnL59amN9tYr7o2a60yFUfzQO3sU
         * position : 0
         * resourceId : {"kind":"youtube#video","videoId":"H6SShCF58-U"}
         */

        private String publishedAt;
        private String channelId;
        private String title;
        private String description;
        private ThumbnailsBean thumbnails;
        private String channelTitle;
        private String playlistId;
        private int position;
        private ResourceIdBean resourceId;

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ThumbnailsBean getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(ThumbnailsBean thumbnails) {
            this.thumbnails = thumbnails;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public void setChannelTitle(String channelTitle) {
            this.channelTitle = channelTitle;
        }

        public String getPlaylistId() {
            return playlistId;
        }

        public void setPlaylistId(String playlistId) {
            this.playlistId = playlistId;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public ResourceIdBean getResourceId() {
            return resourceId;
        }

        public void setResourceId(ResourceIdBean resourceId) {
            this.resourceId = resourceId;
        }

        public static class ThumbnailsBean {
            /**
             * default : {"url":"https://i.ytimg.com/vi/H6SShCF58-U/default.jpg","width":120,"height":90}
             * medium : {"url":"https://i.ytimg.com/vi/H6SShCF58-U/mqdefault.jpg","width":320,"height":180}
             * high : {"url":"https://i.ytimg.com/vi/H6SShCF58-U/hqdefault.jpg","width":480,"height":360}
             * standard : {"url":"https://i.ytimg.com/vi/H6SShCF58-U/sddefault.jpg","width":640,"height":480}
             * maxres : {"url":"https://i.ytimg.com/vi/H6SShCF58-U/maxresdefault.jpg","width":1280,"height":720}
             */

            private DefaultBean defaultX;
            private MediumBean medium;
            private HighBean high;
            private StandardBean standard;
            private MaxresBean maxres;

            public DefaultBean getDefaultX() {
                return defaultX;
            }

            public void setDefaultX(DefaultBean defaultX) {
                this.defaultX = defaultX;
            }

            public MediumBean getMedium() {
                return medium;
            }

            public void setMedium(MediumBean medium) {
                this.medium = medium;
            }

            public HighBean getHigh() {
                return high;
            }

            public void setHigh(HighBean high) {
                this.high = high;
            }

            public StandardBean getStandard() {
                return standard;
            }

            public void setStandard(StandardBean standard) {
                this.standard = standard;
            }

            public MaxresBean getMaxres() {
                return maxres;
            }

            public void setMaxres(MaxresBean maxres) {
                this.maxres = maxres;
            }

            public static class DefaultBean {
                /**
                 * url : https://i.ytimg.com/vi/H6SShCF58-U/default.jpg
                 * width : 120
                 * height : 90
                 */

                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class MediumBean {
                /**
                 * url : https://i.ytimg.com/vi/H6SShCF58-U/mqdefault.jpg
                 * width : 320
                 * height : 180
                 */

                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class HighBean {
                /**
                 * url : https://i.ytimg.com/vi/H6SShCF58-U/hqdefault.jpg
                 * width : 480
                 * height : 360
                 */

                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class StandardBean {
                /**
                 * url : https://i.ytimg.com/vi/H6SShCF58-U/sddefault.jpg
                 * width : 640
                 * height : 480
                 */

                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class MaxresBean {
                /**
                 * url : https://i.ytimg.com/vi/H6SShCF58-U/maxresdefault.jpg
                 * width : 1280
                 * height : 720
                 */

                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class ResourceIdBean {
            /**
             * kind : youtube#video
             * videoId : H6SShCF58-U
             */

            private String kind;
            private String videoId;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }
        }
    }
}
