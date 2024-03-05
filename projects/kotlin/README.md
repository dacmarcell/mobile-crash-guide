
# NewsBee Android Application

NewsBee is an Android application that provides users with the latest news
headlines from around the world. It utilizes the News API to fetch news data
and display it to the users in a user-friendly interface.

## Features

- Fetches top headlines from various news sources.
- Allows users to view news articles with details like author, title, description, and image.
- Supports pagination for loading more news articles.
- Dynamically changes background color when scrolling through news articles.

## Installation

1. Clone the repository to your local machine:

```bash
git clone https://github.com/Shreyank031/NewsApp_AndroidProject.git 
```

## Steps 

1. Open the project in Android Studio.

2. Obtain an API key from newsapi.org and replace the `API_KEY` constant in the `NewsInterface` interface with your API key.

3. Build and run the application on an Android device or emulator.

## Dependencies

- Retrofit: For making network requests and handling API responses.
- Gson: For serializing and deserializing JSON data.
- StackLayoutManager: For implementing a stack-based layout for RecyclerView.
- Glide: For loading and caching images from URLs.
- Allows users to view the full article in a web browser.

## Configuration

To use the News API, you need to obtain an API key from newsapi.org and replace the `API_KEY` constant in the 
`NewsInterface` interface with your API key.

## Credits

Developed by Shreyank (https://github.com/Shreyank031)


## License

This project is licensed under the MIT License. See the LICENSE file for details.
