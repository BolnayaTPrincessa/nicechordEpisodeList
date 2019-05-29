# nicechordEpisodeList

A machine-readable list of all NiceChord episodes. This includes episode numbers, titles and YouTube video codes. Be careful that the videos are arranged in order of releasing, which is a little bit different from the order list on the pack.

好和弦所有集數的機讀列表。當中包含影片集數、影片標題以及YouTube影片代碼。注意影片是按實際發佈次序排列的，跟大包裝上列明的次序有一點分別。

## 格式

此處提供了不同格式的列表，包含：

* 純文本
* * 以“;”開頭的行都是註解。
* * 每欄之間以Tab隔開。第一欄是集數，第二欄是標題（按wiki上的為準，可能與其他地方的標題有出入），第三欄是對應的YouTube影片代碼。
* Java序列化物件
* * 可以以附上的Java腳本序取。使用<code>EpisodeList.load();</code>以載入檔案。使用<code>getTitle(int i)</code>可以取得提定集數的標題。使用<code>getVideoCode(int i)</code>可以取得提定集數的影片代碼。

需要其他格式的話可以自己轉換或者聯絡我，亦歡迎其他人上傳其他格式到這裏。