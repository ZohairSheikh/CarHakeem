module.exports = estimateFloat;

function estimateFloat(predicate) {
  var r = 0, l = predicate.length;

  switch (l) {
    case 1:
      r = predicate[0];
    break;

    case 2:
      r = predicate[0] + predicate[1];
    break;

    case 3:
      r = predicate[0] + predicate[1] + predicate[2];
    break;

    case 4:
      r = predicate[0] + predicate[1] + predicate[2] + predicate[3];
    break;

    default:
      for (var i=0; i<l; i++) {
        r+=predicate[i];
      }
  }

  return r;
}
