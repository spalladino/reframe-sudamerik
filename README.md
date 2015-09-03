# reframe-sudamerik

A [re-frame](https://github.com/Day8/re-frame) application for playing around with re-frame. The app will list products and allow to set up requests for purchases at sudamerik.

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests:

```
lein clean
lein cljsbuild auto test
```

## Production Build

```
lein clean
lein cljsbuild once min
```
