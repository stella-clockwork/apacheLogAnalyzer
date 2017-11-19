使用言語：Java
開発環境：Pleiades All in One 4.7.1a.v20171019 Windows 64bit Full Edition

実行方法：
　アップロードしたjar形式のバイナリを用いる場合、
　java MainControler.jar [-m (hour|host)] [-f ("filename" )*"filename"]
                         [-s "period"] [-e "period"]

-m | --method (hour|host)
　集計方法の指定。
　hourを指定すると1時間ごとのアクセス数、hostを指定するとホスト別のアクセス数を出力する。

-f | --file filenames
　ログファイル名の指定。
　ログファイルを複数指定する場合は' '(半角スペース)で区切る。

-s | --start period
　集計期間を区切る場合に、集計の開始時点とする時刻を設定する。
　指定する際は、"day/month/year:hour"の形式で記述する。
　　例：18/Apr/2017:03

-e | --end period
　集計期間を区切る場合に、集計の終了時点とする時刻を設定する。
　指定方法は-sを参照。
