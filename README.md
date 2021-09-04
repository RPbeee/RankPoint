# RankPoint  
各ランクをポイント量で管理するためのプラグイン。  

## 権限  
このコマンドにはコマンドは二種類しかありません。  
基本コマンドの権限が  
rankpoint.rankpoint  
アドミンコマンドの権限が  
rankpoint.rankpad  
です。  

## 軽い使い方  
### 基本コマンド  
/rankpoint points  自分のポイントの確認  
/rankpoint standd  各ランクに必要なポイント量を確認  
/rankpoint oink    豚さんへの挨拶  
/rankpoint help    ヘルプの表示  

### アドミンコマンド  
/rankpad padd [プレイヤー名] [任意の値]  
特定のプレイヤーに任意の値だけポイントを追加する  
/rankpad pset [プレイヤー名] [任意の値]  
特定のプレイヤーのポイントを任意の値にセットする  
/rankpad reload  
リロードする。これでランクも再設定されるため定期的な実行が必須。  
/rankpad help  
ヘルプを表示  

## ダウンロード  
https://github.com/RPbeee/RankPoint/releases/latest  


## config.ymlの内容について  
config.ymlの前半にはランクごとの必要ポイント、昇格コマンドが設定できます。  
これの設定は必須です。  

コマンドを設定する時は必ずプレイヤー名が入るところに「%s」を入れてください。
例:  
/rankup %s blue  

また、黒に関しては赤の基準ポイントを使って  
設定していますのでポイントの設定は必要ありません。
