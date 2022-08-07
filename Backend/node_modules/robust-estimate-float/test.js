var test = require('tape');
var split = require('robust-split');
var sum = require('two-sum');

var estimate = require('./robust-estimate-float');

test('join', function(t) {
  t.equal(0.3, estimate([0.3]));
  t.end();
});

test('op and join', function(t) {
  for (var i=0.1; i<1; i+=0.1) {
    t.equal(0.1 + i, estimate(sum(0.1, i)));
  }
  t.end();
});

test('split/join', function(t) {
  for (var i = 10.10001; i<15; i+=0.2) {
    t.equal(i, estimate(split(i)));
  }

  t.end();
});

test('large', function(t) {

  var predicate = [];
  for (var i=0; i<10; i++) {
    t.equal(i, estimate(predicate));
    predicate.push(1);
  }

  t.end();
});
