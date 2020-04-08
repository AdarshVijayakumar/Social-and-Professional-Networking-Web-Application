import sys
from TwitterAPI import TwitterAPI, TwitterOAuth

search_string = sys.argv[1]
flag = sys.argv[2]

consumer_key = 'YOUR_CONSUMER_KEY'
consumer_secret = 'YOUR_CONSUMER_SECRET'
access_token_key = 'YOUR_ACCESS_TOKEN_KEY'
access_token_secret = 'YOUR_ACCESS_TOKEN_SECRET'


# Using OAuth1...
twitter = TwitterAPI(consumer_key,
                 consumer_secret,
                 access_token_key,
                 access_token_secret)

print("Screenname:", search_string)
print("Query:", flag)

if flag == 'ScreenName':

    response = [tweet for tweet in twitter.request('statuses/user_timeline',
                                                    {'screen_name': search_string,
                                                     'count': 100})]
    
    tweets = [r for r in response]
    TweetList_screenname = open('C:\\apache-tomcat-7.0.34\\webapps\\project\\TweetsByScreenName.txt', 'w')
    
    for tweet in tweets:
        text = tweet['text']
        try:
            TweetList_screenname.write("%s\n" % str(text))
        except UnicodeEncodeError:
            pass
        
    TweetList_screenname.close()


elif flag == 'QueryString':

    response = twitter.request('search/tweets', {'q': search_string}) 
    tweets = [r for r in response]
    
    TweetsList = open('C:\\apache-tomcat-7.0.34\\webapps\\project\\Tweets.txt', 'w')
    
    for tweet in tweets:
        text = tweet['text']
        try:
            TweetsList.write("%s\n" % str(text))
        except UnicodeEncodeError:
            pass
        
    TweetsList.close()
