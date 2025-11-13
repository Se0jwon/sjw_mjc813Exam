module.exports = {
  rules: {
    'vue/multi-word-component-names': 'off',
  },
  parser: "vue-eslint-parser",
  parserOptions: {
    ecmaVersion: 2020,
    sourceType: 'module', // 👈 allows 'import'
  },
  plugins: ["vue"],
};
