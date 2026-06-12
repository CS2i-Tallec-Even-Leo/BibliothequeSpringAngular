import js from "@eslint/js";

export default [
    // Configuration de base pour JavaScript
    js.configs.recommended,

    {
        files: ["**/*.{js,mjs,cjs}"],
        languageOptions: {
            ecmaVersion: "latest",
            sourceType: "module",
        },
        rules: {
            // Règles personnalisées simples
            "no-console": "warn",
            "no-unused-vars": "warn",
            "no-undef": "error",
        },
    },
];