import path from 'path';
import cp from 'child_process';
import replace from 'replace';

// TODO: this could probably be better...
const branchName = cp.execSync('git rev-parse --abbrev-ref HEAD', { encoding: 'utf8' })
  .match(/.+/g)[0];

// Find all occurrences of PROJECT_NAME in ~/src and replace them with
// the branch name in proper case.
replace({
  regex: 'PROJECT_NAME',
  replacement: branchName
    .match(/\w+/g)
    .map((word) => word[0].toUpperCase() + word.slice(1))
    .join(' '),
  paths: [
    path.resolve(__dirname, '..', 'src')
  ],
  recursive: true,
  silent: false
});

// For package.json
replace({
  regex: 'PROJECT_DASHED_NAME',
  replacement: branchName,
  paths: [
    path.resolve(__dirname, '..', 'package.json')
  ],
  recursive: false,
  silent: false
});
