import js from "@eslint/js";
import tseslint from "typescript-eslint";

export default tseslint.config(
    js.configs.recommended,
    ...tseslint.configs.recommended,  // no parserOptions.project needed

    {
        files: ["**/*.{ts,tsx}"],
        languageOptions: {
            ecmaVersion: "latest",
            sourceType: "module",
        },
        rules: {
            "no-console": "off",
            "@typescript-eslint/no-unused-vars": "warn",
        },
    },

    {
        files: ["**/*.{js,mjs,cjs}"],
        rules: {
            "no-console": "off",
            "no-unused-vars": "warn",
            "no-undef": "error",
        },
    },
);