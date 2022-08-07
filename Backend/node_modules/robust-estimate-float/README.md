# robust-estimate-float

Turn a robust geometric predicate into a float.

__Note__: that the result of this package is an _estimate_ and typical floating point considerations should be made.

This module is really basic.  Essentially the idea is that to convert a robust geometric predicate into a float
we need to sum all of the components.

# install

`npm install robust-estimate-float`

# use

```javascript

var est = require('robust-estimate-float');

console.log(est([1])) // 1

// this is an invalid predicate but demonstrates
// how this package works.
console.log(est([1, 1])) // 2

var sum = require('two-sum');

var r = sum(.2, .1);
console.log(est(r)) // 0.30000000000000004

```

# license

[MIT](LICENSE.txt)
