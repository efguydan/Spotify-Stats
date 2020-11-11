# Spotify Stats

Spotify Stats is an Android Application to display statistics about your listening activity on Spotify

## \[ 🚧 🛠👷🔧 Work in progress 🔧️👷🛠 🚧 \]
- 

## Code Formatting
Code Formatting is done with the gradle spotless plugin, using [ktlint](https://github.com/pinterest/ktlint) as the linter. Other settings for the plugin can be configured in [spotless.gradle](spotless.gradle). To format code, run

```gradle
./gradlew spotlessApply
```

To automate the formatting process every time you commit, you can activate the pre-configured [pre-commit-checks.sh](https://github.com/r4sh33d/threadreaderandroid/src/master/config/pre-commit-checks.sh) hook. To activate, run the following commands:

```gradle
chmod u+x config/pre-commit-checks.sh
ln -s $PWD/config/pre-commit-checks.sh .git/hooks/pre-commit
```