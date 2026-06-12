// eslint.config.mjs
import js from "@eslint/js";

export default [
    js.configs.recommended,
    {
        files: ["**/*.{js,mjs,cjs,ts,tsx}"],  // ← include TS/TSX here
        languageOptions: {
            ecmaVersion: "latest",
            sourceType: "module",
        },
        rules: {
            "no-console": "warn",
            "no-unused-vars": "warn",
            "no-undef": "error",
        },
    },
];