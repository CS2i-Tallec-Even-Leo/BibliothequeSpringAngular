import js from "@eslint/js";
import tseslint from "typescript-eslint";

export default tseslint.config(
    // Base JS rules
    js.configs.recommended,

    // TypeScript-aware rules (handles .ts/.tsx parsing)
    ...tseslint.configs.recommended,

    {
        files: ["**/*.{ts,tsx}"],
        languageOptions: {
            ecmaVersion: "latest",
            sourceType: "module",
            parserOptions: {
                project: true,          // uses your tsconfig.json
                tsconfigRootDir: import.meta.dirname,
            },
        },
        rules: {
            "no-console": "warn",
            "@typescript-eslint/no-unused-vars": "warn",  // replaces no-unused-vars for TS
            // no-undef not needed for TS — the compiler catches this already
        },
    },

    {
        // Keep plain JS config for any .js files
        files: ["**/*.{js,mjs,cjs}"],
        rules: {
            "no-console": "warn",
            "no-unused-vars": "warn",
            "no-undef": "error",
        },
    },
);